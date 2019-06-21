# ImageProcess

# JOS-operating-system

## Introduction
This project is the course project 1 of Berkeley's CS61B: Data Structures (https://people.eecs.berkeley.edu/~jrs/61b/).

In this project you will implement two simple image processing operations on
color images:  blurring and edge detection.  You will use libraries to read and
write files in the TIFF image format.  One option in TIFF files is that they
can be compressed if there are many adjacent pixels of the same color; the
compressed form is called a run-length encoding.  You will write code to
convert an image into a run-length encoding and back.

Each image is a rectangular matrix of color pixels, which are indexed as
follows (for a 4x3 image):

                         ------> x

                     |   -----------------------------
                     |   | 0, 0 | 1, 0 | 2, 0 | 3, 0 |
                   y |   -----------------------------
                     |   | 0, 1 | 1, 1 | 2, 1 | 3, 1 |
                     v   -----------------------------
                         | 0, 2 | 1, 2 | 2, 2 | 3, 2 |
                         -----------------------------
                         
Each pixel has three numbers in the range 0...255 representing the red, green,
and blue intensities of the pixel.  These three bytes are known as the RGB
values of the image.  A pixel in which all three values are zero is pure black,
and a pixel in which all three values are 255 is bright white.

Part I:  Image Blurring and Edge Detection
## Deployment


The Sobel program does edge detection and the Blur program does blurring.

RunLengthEncoding program does conversions between run-length encoding and image with JAVA.

We have provided Java classes to help you see your output images and debug your
implementation of Part I, in these files:

    Blur.java
    Sobel.java

The main() methods in these classes read an image in TIFF format, use your code
to perform blurring and/or edge detection, write the modified image in TIFF
format, and display the input and output images.  You will need to compile them
against the JAI image libraries in the .jar files we have included, which
may require you to add the .jar files to your "classpath".  In Unix:

    javac -cp "jai_core.jar:jai_codec.jar" *.java

Both programs take one or two command-line arguments.  The first argument
specifies the name of an input image file in TIFF format.  (If you specify no
arguments, the programs will remind you how to use them.)  The optional second
argument specifies the number of iterations of your box blurring filter to
perform.  For example, if you run

    java -cp ".:jai_core.jar:jai_codec.jar" Blur image.tiff 3

then Blur will load the image from image.tiff, perform three iterations of
blurring, write the blurred image to a file named blur_image.tiff, and display
the input and output images.  If you omit the second command-line argument, the
default number of iterations is 1.

The Sobel program does Sobel edge detection.  Optionally, it will also perform
iterations of blurring prior to edge detection.  A small amount of blurring
tends to make edge detection more robust in images whose lines of contrast are
not very sharp.  If you run

    java Sobel image.tiff 5

then Sobel will load the image from image.tiff, perform five iterations of
blurring, perform Sobel edge detection on the blurred image, write the blurred
image to a file named blur_image.tiff, write the grayscale-edge image to a file
named edge_image.tiff, and display all three images (1 input, 2 output).  If
you omit the second command-line argument, no blurring is performed, and no
blurred image is written nor displayed.  (Sobel also has an optional third
command-line argument that you shouldn't try until you complete Part III.)

Take flower.tiff as an example:

The original image:

![Alt text](https://github.com/smharb/ImageProcess/raw/master/original.png)

Blur this image with 5 iterations.

java -cp ".:jai_core.jar:jai_codec.jar" Blur flower.tiff 5

![Alt text](https://github.com/smharb/ImageProcess/raw/master/blur.png)

Edge detection with 5 iterations.

java -cp ".:jai_core.jar:jai_codec.jar" Sobel flower.tiff 5

![Alt text](https://github.com/smharb/ImageProcess/raw/master/edge.png)

A large number of large image files can consume a lot of disk space.  Some
PixImages can be stored more compactly if we represent them as "run-length
encodings."  Imagine taking all the rows of pixels in the image, and connecting
them into one long strip.  Think of the pixels as being numbered thusly:

                        -----------------------------
                        |   0  |   1  |   2  |   3  |
                        -----------------------------
                        |   4  |   5  |   6  |   7  |
                        -----------------------------
                        |   8  |   9  |  10  |  11  |
                        -----------------------------

Some images have long strips of pixels of the same color (RGB intensities).
In particular, the grayscale images produced by sobelEdges() can have large
uniform regions, especially where no edges are detected.  Run-length encoding
is a technique in which a strip of identical consecutive pixels (possibly
spanning several rows of the image) are represented as a single record or
object.  For instance, the following strip of intensities:

            ------------------------------------------------------
            | 7 | 7 | 7 | 88 | 88 | 88 | 88 | 88 | 0 | 0 | 0 | 0 |
            ------------------------------------------------------
              0   1   2    3    4    5    6    7   8   9   10  11

could be represented with just three records, each representing one "run":

                             --------------------
                             | 7,3 | 88,5 | 0,4 |
                             --------------------
