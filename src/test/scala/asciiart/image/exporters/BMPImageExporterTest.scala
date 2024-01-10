package asciiart.image.exporters

import asciiart.image.models.image.RGBImage
import asciiart.image.models.pixel.RGBPixel
import org.mockito.ArgumentMatchersSugar.anyInt
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar

class BMPImageExporterTest extends FunSuite with MockitoSugar {
  test("BMPImageExporter should set correct file path and format") {
    val filePath = "test.bmp"
    val exporter = BMPImageExporter(filePath)

    assert(exporter.filePath == filePath)
    assert(exporter.exportFormat == "bmp")
  }

  test("exportImage should successfully export an image") {
    val filePath = "test.bmp"
    val exporter = BMPImageExporter(filePath)

    val mockImage = mock[RGBImage]
    when(mockImage.width).thenReturn(10)
    when(mockImage.height).thenReturn(10)
    when(mockImage.getPixel(anyInt, anyInt)).thenReturn(RGBPixel(255, 255, 255))

    val result = exporter.exportImage(mockImage)

    assert(result.isRight)
  }

  test("exportImage should return error if there is an exception") {
    val filePath = "invalidPath/test.bmp"
    val exporter = BMPImageExporter(filePath)

    val mockImage = mock[RGBImage]
    when(mockImage.width).thenReturn(10)
    when(mockImage.height).thenReturn(10)
    when(mockImage.getPixel(anyInt, anyInt)).thenReturn(RGBPixel(255, 255, 255))

    val result = exporter.exportImage(mockImage)

    assert(result.isLeft)
  }

}
