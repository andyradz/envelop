/*
 * Created by SharpDevelop.
 * User: dp0470
 * Date: 2017-10-10
 * Time: 14:43
 * 
 * To change this template use Tools | Options | Coding | Edit Standard Headers.
 */
using System;
using System.Collections.Generic;
using System.Web.Http;

namespace OWINHost
{
	/// <summary>
	/// Description of ValueController.
	/// </summary>
	public class ValuesController : ApiController
	{
		// GET api/values
		public IEnumerable<string> Get()
		{ 
			return new string[] { "value1", "value2" }; 
		}

		// GET api/values/5
		public string Get(int id)
		{ 
			return "value"; 
		}

		// POST api/values
		public void Post([FromBody]string value)
		{ 
		}

		// PUT api/values/5
		public void Put(int id, [FromBody]string value)
		{ 
		}

		// DELETE api/values/5
		public void Delete(int id)
		{ 
		}
	}
}
