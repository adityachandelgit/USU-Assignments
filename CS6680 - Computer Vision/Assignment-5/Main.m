%%%%%%%%%%%%%%% ----- Problem I.1 ------ %%%%%%%%%%%%%%%%

city = imread('City.jpg');
se = strel('square', 3);
cityDilated = imdilate(city,se);
cityEroed = imerode(city,se);
imshow(cityDilated - cityEroed);
disp('Problem I.1 Explanation');
disp('On dilating the city image, the edges of black pixels will get expanded, and eroding the city image, the edges of black pixels will get reduced.');
disp('So when we substract the eroded image from the dilated one, we will get an image that will have very prominent BORDERS! THICK BORDER!');
disp('-------------Solved problem I.1-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem I.2 ------ %%%%%%%%%%%%%%%%

smallSquares = imread('SmallSquares.tif');
smallSquaresEroded = imerode(smallSquares, strel([0 0 0; 1 1 0; 0 1 0]));
smallSquaresComp = imcomplement(smallSquares);
smallSquaresCompEroded = imerode(smallSquaresComp, strel([0 1 1; 0 0 1; 0 0 0]));
resultImage = smallSquaresEroded & smallSquaresCompEroded;
imshow(resultImage), title('Resultant Image');
disp('Number of foreground pixels: ');
disp(sum(resultImage(:)));
disp('-------------Solved problem I.2-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem I.3 ------ %%%%%%%%%%%%%%%%

wirebond = imread('Wirebond.tif');
figure, subplot(2,2,1), imshow(wirebond), title('Original Image');

wirebondEroed1 = imerode(wirebond, strel('square', 17));
subplot(2,2,2), imshow(wirebondEroed1), title('Desired Image 1');

wirebondEroed2 = imerode(wirebond, strel('square', 10));
subplot(2,2,3), imshow(wirebondEroed2), title('Desired Image 2');

wirebondEroed3 = imerode(wirebond, strel('square', 40));
subplot(2,2,4), imshow(wirebondEroed3), title('Desired Image 3');
disp('-------------Solved problem I.3-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem I.4 ------ %%%%%%%%%%%%%%%%

shapes = imread('Shapes.tif');
figure, subplot(2,2,1), imshow(shapes), title('Original Image');

shapesEroded1 = imerode(shapes, strel('square', 20));
shapesDilated1 = imdilate(shapesEroded1, strel('square', 20));
subplot(2,2,2), imshow(shapesDilated1), title('Desired Image 1');

shapesDilated2 = imdilate(shapes, strel('square', 17));
shapesEroded2 = imerode(shapesDilated2, strel('square', 17));
subplot(2,2,3), imshow(shapesEroded2), title('Desired Image 2');

shapesEroded3 = imerode(shapes, strel('square', 20));
shapesDilated3 = imdilate(shapesEroded3, strel('square', 36));
shapesEroded4 = imerode(shapesDilated3, strel('square', 20));
subplot(2,2,4), imshow(shapesEroded4), title('Desired Image 3');
disp('-------------Solved problem I.4-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem I.5 ------ %%%%%%%%%%%%%%%%

dowels = imread('Dowels.tif');
dowelsOpen = imopen(dowels, strel('disk',5));
dowelsClose = imclose(dowelsOpen, strel('disk',5));
figure, subplot(1,2,1), imshow(dowelsClose), title('Open-Close');

dowelsClose1 = imclose(dowels, strel('disk',5));
dowelsOpen1 = imopen(dowelsClose1, strel('disk',5));
subplot(1,2,2), imshow(dowelsOpen1), title('Close-Open');

disp('Open-Close: First we apply open operation. An open operation is basically an erosion followed by a dilation operation.');
disp('During erosion the small white pixel in the background are eroded and after that during dilation these white background pixels are NOT brought back. So now we have an image with darker background.');
disp('Next Close operation is applied, which is dilation followed by erosion.');
disp('Now dilation is applied to the image with dark background, this further increases the size of white dots and background still remains the same as there arent many small pixels. Then erosion is applied which makes the white dots again to its original size.');
disp(' ');
disp('Close-Open: During close operation, the image is first dilated, which increases the size of white pixels. So the white plxels in the background become bigger, and some of them get joined.');
disp('Now erosion takes place, this reduces the size of white parts. Some of the background pixels got merged in the pervious step, so they wont go away during erosion.');
disp('Next Open is applied, which is erosion followed by dilation. This will make the image even more clearer with emphasis on white parts');
disp(' ');
disp('In Conclusion: Open-Close reduces white nosie in the image, whereas Close-Open reduces noise and makes the image little darker.');
disp(' ');

dowelsLoopOC = imread('Dowels.tif');
for size = 2:5
    dowelsLoopOC = imclose(imopen(dowelsLoopOC, strel('disk',size)), strel('disk',size));
end
figure, subplot(1,2,1), imshow(dowelsLoopOC), title('Open-Close (2:5)');

dowelsLoopCO = imread('Dowels.tif');
for size = 2:5
    dowelsLoopCO = imopen(imclose(dowelsLoopCO, strel('disk',size)), strel('disk',size));
end
subplot(1,2,2), imshow(dowelsLoopCO), title('Close-Open (2:5)');

disp('Open-Close (2-5): On applying Open-Close repeatedly with increasing radius size, the background reduces and becomes smoother on each iteration. Same goes for foreground image as well. This is because redius size determines the size of dilation and erorsion in open close operation. Multiple Open-Close operation with increasing radius makes the image little darker too');
disp('Close-Open (2-5): On applying Close-Open repeatedly with increasing radius size, the background reduces and becomes smoother on each iteration. Same goes for foreground image as well. This is because redius size determines the size of dilation and erorsion in close open operation. Multiple Close-Open operation with increasing radius makes the image little whiter too');
disp('-------------Solved problem I.5-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem II.1 ------ %%%%%%%%%%%%%%%%

[labelIm, num] = FindComponentLabels(imread('Ball.tif'), strel('square', 3));
disp('Total number of connected components is:');
disp(num)
imtool(labelIm, []);
disp('-------------Solved problem II.1-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem II.2 ------ %%%%%%%%%%%%%%%%

ball = imread('Ball.tif');
connectedComponentsBall = bwconncomp(ball);
ballLabels = labelmatrix(connectedComponentsBall);
disp('Total number of connected components is:');
disp(connectedComponentsBall.NumObjects);
imtool(ballLabels, []);
disp('-------------Solved problem II.2-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem II.3 ------ %%%%%%%%%%%%%%%%

IM2 = imclearborder(ball);
A = ball - IM2;
num = bwlabel(A);
disp('Number of pixels residing on the border is: ');
disp(max(num(:)));
figure, subplot(1,2,1), imshow(ball), title('Original Image');
subplot(1,2,2), imshow(A), title('Border Elements');
disp('-------------Solved problem II.3-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem II.4 ------ %%%%%%%%%%%%%%%%

B = IM2;
numPixels = cellfun(@numel,connectedComponentsBall.PixelIdxList);
commonSize = mode(numPixels(:));
for i = 1:connectedComponentsBall.NumObjects
    if(commonSize * 1.1 > numPixels(i))
        B(connectedComponentsBall.PixelIdxList{i}) = 0;
    end
end
num = bwlabel(B);
disp('Number of overlapping connected particles that are not on the border is: ');
disp(max(num(:)));
figure, subplot(1,2,1), imshow(ball), title('Original Image');
subplot(1,2,2), imshow(B), title('Non-border overlapping connected particles ');
disp('-------------Solved problem II.4-----------------');









