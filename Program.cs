/*
 * Created by SharpDevelop.
 * User: dp0470
 * Date: 2017-10-10
 * Time: 14:41
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Net.Http;
using Microsoft.Owin.Hosting;

namespace OWINHost
{
	class Program
	{
		public static void Main(string[] args)
		{
			string baseAddress = "http://localhost:9000/"; 

			// Start OWIN host 
			using (WebApp.Start<Startup>(url: baseAddress)) { 
				// Create HttpCient and make a request to api/values 
				HttpClient client = new HttpClient(); 

				var response = client.GetAsync(baseAddress + "api/values").Result; 

				Console.WriteLine(response); 
				Console.WriteLine(response.Content.ReadAsStringAsync().Result); 
				Console.ReadLine(); 
			} 
		}
	}
}