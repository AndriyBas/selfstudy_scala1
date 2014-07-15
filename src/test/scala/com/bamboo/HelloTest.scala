package com.bamboo

import org.scalatest.FunSuite

/**
 * Created by bamboo on 11.07.14.
 */
class HelloTest extends FunSuite {


  test("Hello$sayHello(String) works correctly") {
    val hello = new Hello
    assert(hello.sayHello("Scala") == "hello, Scala")
  }

  test("opa") {
    val e = new ex1
    e.fixedPoint(x => x)(1)
    assert(true)
  }
}
