package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._


class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "return a successful response" in {
      val controller = inject[HomeController]
      val name = "John"
      val request = FakeRequest(GET, s"/hello/$name")
      val result = controller.hello(name).apply(request)

      status(result) mustBe OK
    }

    "return a response containing the name" in {
      val controller = inject[HomeController]
      val name = "John"
      val request = FakeRequest(GET, s"/hello/$name")
      val result = controller.hello(name).apply(request)

      contentAsString(result) must include(name)
    }
  }
}