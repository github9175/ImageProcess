# ImageProcess

The Sobel program does edge detection and the Blur program does blurring.

RunLengthEncoding program does conversions between run-length encoding and image with JAVA.

Take flower.tiff as an example:

The original image:

![Alt text](https://github.com/smharb/ImageProcess/raw/master/original.png)

Blur this image with 5 iterations.
java -cp ".:jai_core.jar:jai_codec.jar" Blur flower.tiff 5
![Alt text](https://github.com/smharb/ImageProcess/raw/master/blur.png)

Edge detection with 5 iterations.
java -cp ".:jai_core.jar:jai_codec.jar" Sobel flower.tiff 5
![Alt text](https://github.com/smharb/ImageProcess/raw/master/edge.png)
