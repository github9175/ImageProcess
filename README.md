# ImageProcess

# JOS-operating-system

## Introduction
This project is the course project 1 of Berkeley's CS61B: Data Structures (https://people.eecs.berkeley.edu/~jrs/61b/).

We implement two simple image processing operations on color images in this project:  blurring and edge detection.  We use libraries to read and write files in the TIFF image format.  One option in TIFF files is that they can be compressed if there are many adjacent pixels of the same color; the compressed form is called a run-length encoding.  We write code to
convert an image into a run-length encoding and back.

## Image Blurring and Edge Detection

The Sobel program (Blur.java) does edge detection and the Blur program (Sobel.java) does blurring.

You will need to compile them against the JAI image libraries in the .jar files we have included, which may require you to add the .jar files to your "classpath".  In Unix:
```{r}
    javac -cp "jai_core.jar:jai_codec.jar" *.java
```
Both programs take one or two command-line arguments.  The first argument specifies the name of an input image file in TIFF format.  (If you specify no arguments, the programs will remind you how to use them.)  The optional second argument specifies the number of iterations of your box blurring filter to perform.  For example, if you run
```{r}
    java -cp ".:jai_core.jar:jai_codec.jar" Blur image.tiff 3
```
then Blur will load the image from image.tiff, perform three iterations of blurring, write the blurred image to a file named blur_image.tiff, and display the input and output images.  If you omit the second command-line argument, the default number of iterations is 1.

The Sobel program does Sobel edge detection. Optionally, it will also perform iterations of blurring prior to edge detection.  A small amount of blurring tends to make edge detection more robust in images whose lines of contrast are not very sharp.  If you run
```{r}
    java Sobel image.tiff 5
````
then Sobel will load the image from image.tiff, perform five iterations of blurring, perform Sobel edge detection on the blurred image, write the blurred image to a file named blur_image.tiff, write the grayscale-edge image to a file named edge_image.tiff, and display all three images (1 input, 2 output).  If you omit the second command-line argument, no blurring is performed, and no blurred image is written nor displayed. 

## RunLengthEncoding Compression

RunLengthEncoding program does conversions between run-length encoding and image with JAVA.

Some images have long strips of pixels of the same color (RGB intensities). In particular, the grayscale images produced by sobelEdges() can have large uniform regions, especially where no edges are detected.  Run-length encoding is a technique in which a strip of identical consecutive pixels (possibly spanning several rows of the image) are represented as a single record or object.  For instance, the following strip of intensities:
```{r}
            ------------------------------------------------------
            | 7 | 7 | 7 | 88 | 88 | 88 | 88 | 88 | 0 | 0 | 0 | 0 |
            ------------------------------------------------------
              0   1   2    3    4    5    6    7   8   9   10  11
```
could be represented with just three records, each representing one "run":
```{r}
                             --------------------
                             | 7,3 | 88,5 | 0,4 |
                             --------------------
```

If the Sobel program is given three (or more) command-line arguments, regardless of what the third argument is, it will write the grayscale-edge image twice, once as an uncompressed TIFF file and once as a run-length encoded TIFF file.  The two TIFF files will be different and have different lengths, but the images should look identical. 
