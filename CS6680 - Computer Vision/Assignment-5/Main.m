%%%%%%%%%%%%%%% ----- Problem I.1 ------ %%%%%%%%%%%%%%%%

% city = imread('City.jpg');
% se = strel('square', 3);
% cityDilated = imdilate(city,se);
% cityEroed = imerode(city,se);
% imshow(cityDilated - cityEroed);


%%%%%%%%%%%%%%% ----- Problem I.2 ------ %%%%%%%%%%%%%%%%


%%%%%%%%%%%%%%% ----- Problem I.3 ------ %%%%%%%%%%%%%%%%

% wirebond = imread('Wirebond.tif');
% figure, subplot(2,2,1), imshow(wirebond), title('Original Image');
% 
% wirebondEroed1 = imerode(wirebond, strel('square', 17));
% subplot(2,2,2), imshow(wirebondEroed1), title('Desired Image 1');
% 
% wirebondEroed2 = imerode(wirebond, strel('square', 10));
% subplot(2,2,3), imshow(wirebondEroed2), title('Desired Image 2');
% 
% wirebondEroed3 = imerode(wirebond, strel('square', 50));
% subplot(2,2,4), imshow(wirebondEroed3), title('Desired Image 3');


%%%%%%%%%%%%%%% ----- Problem I.4 ------ %%%%%%%%%%%%%%%%

% shapes = imread('Shapes.tif');
% figure, subplot(2,2,1), imshow(shapes), title('Original Image');
% 
% shapesEroded1 = imerode(shapes, strel('square', 20));
% shapesDilated1 = imdilate(shapesEroded1, strel('square', 20));
% subplot(2,2,2), imshow(shapesDilated1), title('Desired Image 1');
% 
% shapesDilated2 = imdilate(shapes, strel('square', 17));
% shapesEroded2 = imerode(shapesDilated2, strel('square', 17));
% subplot(2,2,3), imshow(shapesEroded2), title('Desired Image 2');
% 
% shapesEroded3 = imerode(shapes, strel('square', 20));
% shapesDilated3 = imdilate(shapesEroded3, strel('square', 36));
% shapesEroded4 = imerode(shapesDilated3, strel('square', 20));
% subplot(2,2,4), imshow(shapesEroded4), title('Desired Image 3');


%%%%%%%%%%%%%%% ----- Problem I.5 ------ %%%%%%%%%%%%%%%%

dowels = imread('Dowels.tif');
dowelsOpen = imopen(dowels, strel('disk',5));
dowelsClose = imclose(dowelsOpen, strel('disk',5));
figure, subplot(1,2,1), imshow(dowelsClose), title('Open-Close');

dowelsClose1 = imclose(dowels, strel('disk',5));
dowelsOpen1 = imopen(dowelsClose1, strel('disk',5));
subplot(1,2,2), imshow(dowelsOpen1), title('Close-Open');

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









