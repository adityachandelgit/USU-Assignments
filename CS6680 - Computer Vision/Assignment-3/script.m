% %%%%%%%%%%%%% ------ Problem I 1 ------ %%%%%%%%%%%%
originalCircuit = imread('Circuit.jpg');
weightedFilter = [1 2 1; 2 1 2; 1 2 1];
standardFilter = [1 1 1 1 1; 
            1 1 1 1 1; 
            1 1 1 1 1; 
            1 1 1 1 1; 
            1 1 1 1 1];
weightedFilteredImage = AverageFiltering(originalCircuit, weightedFilter);
standardFilteredImage = AverageFiltering(originalCircuit, standardFilter);
figure,
subplot(1,3,1), imshow(originalCircuit), title('Original Image');
subplot(1,3,2), imshow(weightedFilteredImage), title('Average Filtered Image');
subplot(1,3,3), imshow(standardFilteredImage), title('Weighted Filtered Image');
pause;

%%%%%%%%%%%%% ------ Problem I 2 ------ %%%%%%%%%%%%
weightedMedianFIilter = [1 2 1; 2 4 2; 1 2 1];
standardMedianFilter = [1 1 1 1 1; 
            1 1 1 1 1; 
            1 1 1 1 1; 
            1 1 1 1 1; 
            1 1 1 1 1];
figure,
subplot(1,3,1), imshow(originalCircuit), title('Original Image');
subplot(1,3,2), imshow(MedianFiltering(originalCircuit, standardMedianFilter)), title('Standard Median Filtered Image');
subplot(1,3,3), imshow(MedianFiltering(originalCircuit, weightedMedianFIilter)), title('Weighted Median Filtered Image');
pause;

% %%%%%%%%%%%%% ------ Problem I 3 ------ %%%%%%%%%%%%
strong = [1 1 1; 1 -8 1; 1 1 1];
Moon = imread('Moon.jpg');
MoonDouble = double(Moon);
MoonFiltered = imfilter(MoonDouble, strong);
MoonEnhanced = MoonDouble - MoonFiltered;
MoonEnhanced = uint8(MoonEnhanced);
figure,
subplot(2,2,1), imshow(Moon), title('Original Image');
subplot(2,2,2), imshow(uint8(MoonFiltered)), title('Filtered Image');
subplot(2,2,3), imshow(MoonFiltered,[]), title('Scaled Filtered Image');
subplot(2,2,4), imshow(MoonEnhanced), title('Enhanced Image');
pause;

%%%%%%%%%%%%% ------ Problem II 1 ------ %%%%%%%%%%%%
Rice = imread('Rice.jpg');

%Method 1
sobelOp = Sobel(Rice, 1);
%Method 2
%sobelOp = Sobel(Rice, 2);
disp('The mean pixel value of the Rice image is 108, with standard deviation is close to 5.')
disp('Also while observing the histogram, I found out that most of the edges lie around 111 pixel value.')
disp('This is the reason why I have chosen the threshold value to be 111.')
pause;

% %%%%%%%%%%%%% ------ Problem II 2 ------ %%%%%%%%%%%%
calHistOp = CalEdgeHist(Rice, 20);
bar(calHistOp)
subplot(1,3,1), imshow(Rice), title('Original Image');
subplot(1,3,2), imshow(sobelOp), title('Sobel Filtered Image');
subplot(1,3,3), bar(calHistOp, 'hist'), title('Edge Histogram');
pause;

%%%%%%%%%%%%% ------ Problem 3 ------ %%%%%%%%%%%%
text = imread('Text.gif');
textFiltered = text;
textFiltered(textFiltered >= 82 & textFiltered <= 255) = 255;
figure,
subplot(1,3,1), imshow(text), title('Original text.gif');
subplot(1,3,2), histogram(text), title('Histogram to detect the range noise');
subplot(1,3,3), imshow(textFiltered), title('Cleaned text.gif');
disp('On visually inspecting the image, I found out that most of the "noisy" pixel were in greyish range and non-noise text were in blackish range')
disp('Then on observing the histogram for text.gif, I found out that the non-noise text were in range below 82.')
disp('So to preserve the non-noise text and remove the noisy text, I used thresholding to set pixel above 82 to 255(white)')
disp('This way the noise is removed from text.gif')
disp('This thresholding method does not work with text1.gif and text2.gif because most of noisy pixel values overlap with non-noisy text pixel values, making it difficult to differentiate between both.')