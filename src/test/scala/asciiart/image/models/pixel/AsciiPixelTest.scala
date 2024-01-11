package asciiart.image.models.pixel

import asciiart.StandardTestsGroup
import org.scalatest.FunSuite

class AsciiPixelTest extends FunSuite {

  test("AsciiPixel should have correct value", StandardTestsGroup) {
    val pixel = AsciiPixel('a')
    assert(pixel.value == 'a')
  }

  test("AsciiPixel should convert to correct string", StandardTestsGroup) {
    val pixel = AsciiPixel('a')
    assert(pixel.toString == "a")
  }

}
