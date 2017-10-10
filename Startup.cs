/*
 * Created by SharpDevelop.
 * User: dp0470
 * Date: 2017-10-10
 * Time: 14:42
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using Owin; 
using System.Web.Http; 

namespace OWINHost
{
	/// <summary>
	/// Description of Startup.
	/// </summary>
	public class Startup
	{
		// This code configures Web API. The Startup class is specified as a type
		// parameter in the WebApp.Start method.
		public void Configuration(IAppBuilder appBuilder)
		{ 
			// Configure Web API for self-host. 
			HttpConfiguration config = new HttpConfiguration(); 
			config.Routes.MapHttpRoute(
				name: "DefaultApi", 
				routeTemplate: "api/{controller}/{id}", 
				defaults: new { id = RouteParameter.Optional } 
			); 

			appBuilder.UseWebApi(config); 
		}
	}
}
