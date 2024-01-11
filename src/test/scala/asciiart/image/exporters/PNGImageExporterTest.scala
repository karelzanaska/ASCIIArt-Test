package asciiart.image.exporters

import asciiart.StandardTestsGroup
import asciiart.image.models.image.RGBImage
import asciiart.image.models.pixel.RGBPixel
import org.mockito.ArgumentMatchersSugar.anyInt
import org.mockito.MockitoSugar
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.FunSuite


class PNGImageExporterTest extends FunSuite with MockitoSugar {

  test("PNGImageExporter should set correct file path and format", StandardTestsGroup) {
    val filePath = "test.png"
    val exporter = PNGImageExporter(filePath)

    assert(exporter.filePath == filePath)
    assert(exporter.exportFormat == "png")
  }

  test("exportImage should successfully export an image", StandardTestsGroup) {
    val filePath = "test.png"
    val exporter = PNGImageExporter(filePath)

    val mockImage = mock[RGBImage]
    when(mockImage.width).thenReturn(10)
    when(mockImage.height).thenReturn(10)
    when(mockImage.getPixel(anyInt, anyInt)).thenReturn(RGBPixel(255, 255, 255))

    val result = exporter.exportImage(mockImage)

    assert(result.isRight)
  }

  test("exportImage should return error if there is an exception", StandardTestsGroup) {
    val filePath = "invalidPath/test.png"
    val exporter = PNGImageExporter(filePath)

    val mockImage = mock[RGBImage]
    when(mockImage.width).thenReturn(10)
    when(mockImage.height).thenReturn(10)
    when(mockImage.getPixel(anyInt, anyInt)).thenReturn(RGBPixel(255, 255, 255))

    val result = exporter.exportImage(mockImage)

    assert(result.isLeft)
  }
}
