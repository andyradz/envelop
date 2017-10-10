/*
 * Created by SharpDevelop.
 * User: dp0470
 * Date: 2017-10-10
 * Time: 14:55
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Reflection;
using System.Web.Http;
using Microsoft.Owin.Hosting;
using Ninject;
using Owin;
using Topshelf;
using Topshelf.ServiceConfigurators;

namespace WebApiService
{
	
	//http://johnatten.com/2015/01/19/asp-net-web-api-understanding-owinkatana-authenticationauthorization-part-i-concepts/
	class Program
	{
		static int Main(string[] args)
		{
			var exitCode = HostFactory.Run(c => {
				c.Service<MyService>(service => {
					ServiceConfigurator<MyService> s = service;
					s.ConstructUsing(() => new MyService());
					s.WhenStarted(a => a.Start());
					s.WhenStopped(a => a.Stop());
					c.RunAsLocalSystem();
					c.SetDescription("This is a demonstration of a Windows Service using Topshelf.");
					c.SetDisplayName("Self Host Web API Demo");
					c.SetServiceName("AspNetSelfHostDemo");
				});
			});
			return (int)exitCode;
		}
	}

	public class MyService
	{
		private IDisposable app;
		public void Start()
		{
			app = WebApp.Start("http://localhost:5000/");
		}
		public void Stop()
		{
			app.Dispose();
		}
	}

	public class Startup
	{
		public void Configuration(IAppBuilder app)
		{
			var config = new HttpConfiguration();
			config.MapHttpAttributeRoutes();
			config.Formatters.Remove(config.Formatters.XmlFormatter);
			config.Formatters.Add(config.Formatters.JsonFormatter);
			//config.EnableSystemDiagnosticsTracing();
			//app.UseNinjectMiddleware(CreateKernel).UseNinjectWebApi(config);
		}

		private static IKernel CreateKernel()
		{
			var kernel = new StandardKernel();
			kernel.Load(Assembly.GetExecutingAssembly());
			DependencyInjection(kernel);
			return kernel;
		}

		private static void DependencyInjection(IKernel kernel)
		{
			kernel.Bind<ISampleDependency>().To<SampleDependency1>();
		}
	}

	public class SampleController : ApiController
	{
		private ISampleDependency _dependency;
		public SampleController(ISampleDependency dependency)
		{
			_dependency = dependency;
		}
		[Route("")]
		[HttpGet]
		public IHttpActionResult GetRoot()
		{
			return Ok<string>("This is root.");
		}
		[Route("{id:int}")]
		[HttpGet]
		public IHttpActionResult GetSquare(int id)
		{
			int value;
			try {
				value = _dependency.Square(id);
			} catch {
				return NotFound();
			}
			return Ok<int>(value);
		}
		[Route("{id}")]
		[HttpGet]
		public IHttpActionResult GetString(string id)
		{
			return Ok<string>(string.Format("get string: {0}", id));
		}
	}

	public interface ISampleDependency
	{
		int Square(int id);
	}

	public class SampleDependency1 : ISampleDependency
	{
		public int Square(int id)
		{
			return id * id;
		}
	}

	public class SampleDependency2 : ISampleDependency
	{
		public int Square(int id)
		{
			return id * id * id;
		}
	}
}