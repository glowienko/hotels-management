package edu.wut.hotels.management

import org.scalatest.FunSuite

class GeneratorTest extends FunSuite {
  test("Generator.add") {
    assert(Generator.add(2, 2) === 4)
  }
}