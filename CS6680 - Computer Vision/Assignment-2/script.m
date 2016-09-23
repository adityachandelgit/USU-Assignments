food = imread('food.jpg');

%%%%%%%%---- Problem 1 ----%%%%%%%%%
display('Solving Problem 1....');
[scaledFood, transFunc] = Scaling(food, [0.0 0.99]);
plot(transFunc)
title('Scaling() transFunc')
xlabel('Pixel number')
ylabel('Pixel value after transformation')
fprintf('Problem 1 Solved!\n\n');
pause;

%%%%%%%%---- Problem 2 ----%%%%%%%%%
display('Solving Problem 2....');
figure,
subplot(1,2,1), imshow(scaledFood), title('My Scaled Image');
subplot(1,2,2), imshow(imadjust(food, [double(min(food(:)))/255 double(max(food(:)))/255], [0.0 0.99])), title('Matlabs Scaled Image');
imwrite(imadjust(food, [double(min(food(:)))/255 double(max(food(:)))/255], [0.0 0.99]),'matScaledFood.jpg');
fprintf('Problem 2 Solved!\n\n');
pause;

%%%%%%%%---- Problem 3 ----%%%%%%%%%
display('Solving Problem 3....');
[myHistogram, myNormHistogram] = CalHist(scaledFood, 3); % Calculate both Non-Normalized Histogram and Normalized Histogram for scaledFood
[matScaledFoodNormHist] = CalHist(imread('matScaledFood.jpg'), 2); % Calculate Normalized Histogram for matScaledFood
figure,
subplot(1,2,1), bar(cell2mat(myNormHistogram.keys), cell2mat(myNormHistogram.values)), title('Normalized Histogram for scaledFood'), xlabel('Pixel Value'), ylabel('Normalized Pixel Count/Frequency');
subplot(1,2,2), bar(cell2mat(matScaledFoodNormHist.keys), cell2mat(matScaledFoodNormHist.values)), title('Normlized Histogram for matScaledFood'), xlabel('Pixel Value'), ylabel('Normalized Pixel Count/Frequency');
fprintf('Problem 3 Solved!\n\n');
pause;

%%%%%%%%---- Problem 4 ----%%%%%%%%%
display('Solving Problem 4....');
tic
[equalizedFood, transFunc] = HistEqualization(food);
toc
t1 = toc;
fprintf('Problem 4 Solved!\n\n');
pause;

%%%%%%%%---- Problem 5 ----%%%%%%%%%
display('Solving Problem 5....');
tic
[J, T] = histeq(food);
toc
t2 = toc;
T = round(T * 255);
tx = round(((t1 - t2) / t2) * 100);
fprintf('~Matlabs inbuilt Histogram Equilization function is %d percent faster than HistEqualization function~\n', tx);
fprintf('~Few of the values produced by HistEqualization function"s transFunc deviate by 1 or 2 in comparison to Matlabs inbuilt histeq"s transFunc~\n');

figure,
subplot(1,2,1), imshow(equalizedFood), title('HistEqualization');
subplot(1,2,2), imshow(histeq(food)), title('histeq()');

range = 0:255;
figure,
plot(range, transpose(transFunc), range, T), title('Transformation functions for HistEqualization & histeq'), xlabel('Pixel Number'), ylabel('Pixel Value')
legend('HistEqualization','histeq')

fprintf('Problem 5 Solved!\n\n');
pause;
close all;
clear;
disp('---Closed & Cleared---');

