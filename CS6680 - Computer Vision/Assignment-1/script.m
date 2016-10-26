%%%%%%%% ---- Problem 1 ---- %%%%%%%%%

A = imread('peppers.bmp');
figure, imshow(A);
title('RGB Original Image');
disp('Problem 1 Solved.');
pause;


%%%%%%%% ---- Problem 2 ---- %%%%%%%%%

B = rgb2gray(A);
TB = transpose(B);

[~, col] = size(B);
LH = B(:,1:col/2);
RH = B(:,(col/2)+1:col);
VB = horzcat(RH, LH);
FB = flip(B, 2);

figure,
subplot(2,2,1), imshow(B), title('B');
subplot(2,2,2), imshow(TB), title('TB');
subplot(2,2,3), imshow(VB), title('VB');
subplot(2,2,4), imshow(FB), title('FB');

disp('Problem 2 Solved.');
pause;


%%%%%%%% ---- Problem 3 ---- %%%%%%%%%

[maxValue, minValue, meanValue, medianValue] = FindInfo(B);

theMaximum = max(max(B));
theMinimum = min(min(B));
theMean = mean(B(:));
theMedian = median(B, 2);

if isequal(maxValue, theMaximum)
    display('---Max values matched---');
end
if isequal(minValue, theMinimum)
    display('---Min values matched---');
end
if isequal(meanValue, theMean)
    display('---Mean matched---');
end
if isequal(theMedian, medianValue)
    display('---Median matched---');
end
disp('Problem 3 Solved.');
pause;


%%%%%%%% ---- Problem 4 ---- %%%%%%%%%

C = im2double(B);


figure, imshow(C);
title('Normalized GreyScale Image');

[row, col] = size(C);
T1 = C(: , 1:col/4);
T1 = T1.^0.5;
T2 = C(: , (col/4 + 1):(3*col/4));
T3 = C(: , (3*col/4 + 1):(col));
T3 = T3.^1.5;
D = horzcat(T1,T2,T3);
figure, imshow(D);
title('Processed GreyScale Image');
imwrite(D,'X_D.jpg');
display('---X_D.jpg written to HDD---');

disp('Problem 4 Solved.');
pause;


%%%%%%%% ---- Problem 5 ---- %%%%%%%%%

threshold = 0.3;

bw1 = C > threshold;

bw2 = C;
bw2(C > threshold) = 1;
bw2(C <= threshold) = 0;

bw3 = im2bw(C, threshold);

figure,
subplot(1,3,1), imshow(bw1), title('My first method');
subplot(1,3,2), imshow(bw2), title('My second method');
subplot(1,3,3), imshow(bw3), title('Matlab method');

disp('---Both of my methods worked---');
disp('Problem 5 Solved.');
pause;


%%%%%%%% ---- Problem 6 ---- %%%%%%%%%

BA = BlurImage(A);
BB = BlurImage(B);

figure,
subplot(2,2,1), imshow(A), title('A');
subplot(2,2,2), imshow(B), title('B');
subplot(2,2,3), imshow(BA), title('BA');
subplot(2,2,4), imshow(BB), title('BB');

disp('Problem 6 Solved.');
pause;


%%%%%%%% ---- Problem 7 ---- %%%%%%%%%

close all;
clear;
disp('---Closed & Cleared---');

