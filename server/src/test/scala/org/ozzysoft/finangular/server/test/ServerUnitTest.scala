package org.ozzysoft.finangular.server.test

import org.scalatest.FunSuite

class ServerUnitTest extends FunSuite {

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }
}
