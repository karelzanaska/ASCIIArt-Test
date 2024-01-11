package asciiart.image.importers

import asciiart.StandardTestsGroup
import asciiart.image.models.image.RGBImage
import org.scalatest.FunSuite

class RandomImageGeneratorImporterTest extends FunSuite {

  test("importImage should return RGBImage", StandardTestsGroup) {
    val result = RandomImageGeneratorImporter().importImage()
    assert(result.isRight)
    result.foreach { image =>
      assert(image.isInstanceOf[RGBImage])
    }
  }

  test("importImage should create random pixel grid", StandardTestsGroup) {
    val result1 = RandomImageGeneratorImporter().importImage()
    val result2 = RandomImageGeneratorImporter().importImage()
    assert(result1 != result2)
  }
}
