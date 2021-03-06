// generated automatically by Taurus

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TaurusSimulation_139957342270416 extends Simulation {
    val _t_concurrency = Integer.getInteger("concurrency", 1).toInt
    val _t_ramp_up = Integer.getInteger("ramp-up", 0).toInt
    val _t_hold_for = Integer.getInteger("hold-for", 0).toInt
    val _t_iterations = Integer.getInteger("iterations")

    val _duration = _t_ramp_up + _t_hold_for

    var httpConf = http.baseURL("http://blazedemo.com")
		.header("H1", "V1")

    var _scn = scenario("Taurus Scenario")

    var _exec = exec(
			http("/reserve.php").post("/reserve.php")
				.header("H2", "V2")
				.body(StringBody(""""Body Content"""))
				.check(
					substring("""bootstrap.min""").notExists)
		).pause(0).exec(
			http("/").get("/")
		).pause(0)

    if (_t_iterations == null)
        _scn = _scn.forever{_exec}
     else
        _scn = _scn.repeat(_t_iterations.toInt){_exec}

    val _users =
        if (_t_ramp_up > 0)
            rampUsers(_t_concurrency) over (_t_ramp_up seconds)
        else
            atOnceUsers(_t_concurrency)

    var _setUp = setUp(_scn.inject(_users).protocols(httpConf))

    if (_duration > 0)
        _setUp.maxDuration(_duration)
}
