close all;
clear;

%%%%%%%%%%%%%%% ----- Problem 1.1 ------ %%%%%%%%%%%%%%%%

Ball = imread('ball.bmp');
BallHsv = rgb2hsv(Ball);
H = BallHsv(:,:,1);
V = BallHsv(:,:,3);
BallBwHue = H < 0.7 & H > .4;
BallRemBorder = ~(imclearborder(~imdilate(BallBwHue, strel('square',21))));
CC = bwconncomp(~BallRemBorder);
[~,idx] = max(cellfun(@numel,CC.PixelIdxList));
BallRemBigObj = BallRemBorder;
BallRemBigObj(CC.PixelIdxList{idx}) = 1;
figure, subplot(2,3,1), imshow(BallHsv),title('Ball HSV');
subplot(2,3,2), imshow(H),title('Hue Image');
subplot(2,3,3), imshow(BallBwHue),title('Ball BW');
subplot(2,3,4), imshow(BallRemBorder),title('Ball Noise Removed');
subplot(2,3,5), imshow(BallRemBigObj),title('Final Ball Image');
subplot(2,3,6), imshow(BallRemBigObj),title('Centroid Ball');
centroid = regionprops(~BallRemBigObj,'Centroid');
hold on
plot(centroid.Centroid(1),centroid.Centroid(2),'x','MarkerSize',5);
figure,imshow(Ball);
hold on
plot(centroid.Centroid(1),centroid.Centroid(2),'x','MarkerSize',10);
disp('-------------Solved problem 1.1-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem 1.2 ------ %%%%%%%%%%%%%%%%

figure(3);
BallBwVal = V < 0.4 & V > .1;
BallEroded = imerode(~BallBwVal, strel('square',3));
BallDilated = imdilate(BallEroded, strel('square',5));
BallNoBorder = ~(imclearborder(~BallDilated));
BallOnlyShadow = BallNoBorder;
stats = regionprops(~BallOnlyShadow);
CC = bwconncomp(~BallOnlyShadow);
for i=1:length(stats)
    if(centroid.Centroid(1) - stats(i).Centroid(1) > 0.5 * centroid.Centroid(1))
        if(centroid.Centroid(2) - stats(i).Centroid(2) > 0.5 * centroid.Centroid(2))
            BallOnlyShadow(CC.PixelIdxList{i}) = 1;
        end
    end
end

subplot(2,3,1),imshow(BallHsv),title('Ball HSV');
subplot(2,3,2),imshow(V),title('Ball (Value)');
subplot(2,3,3),imshow(BallBwVal),title('Ball Thresholded (Value)');
subplot(2,3,4),imshow(BallEroded),title('Ball BG Removed');
subplot(2,3,5),imshow(BallNoBorder),title('Ball Cleaned');
subplot(2,3,6),imshow(BallOnlyShadow),title('Ball Only Shadow');
Ball(find(BallOnlyShadow == 0)) = 190;
figure, imshow(Ball),title('Shadow colored Image');
disp('-------------Solved problem 1.2-----------------');
pause;

%%%%%%%%%%%%%%% ----- Problem 2.1 ------ %%%%%%%%%%%%%%%%

E1 = imread('Elephant1.jpg');
E2 = imread('Elephant2.jpg');
H1 = imread('Horse1.jpg');
H2 = imread('Horse2.jpg');

HistE1 = CalNormalizedHSVHist(E1, 4, 4, 4);
HistE2 = CalNormalizedHSVHist(E2, 4, 4, 4);
HistH1 = CalNormalizedHSVHist(H1, 4, 4, 4);
HistH2 = CalNormalizedHSVHist(H2, 4, 4, 4);

figure, subplot(1,4,1), plot(HistE1), title('Histogram Elephant1');
subplot(1,4,2), plot(HistE2), title('Histogram Elephant2');
subplot(1,4,3), plot(HistH1), title('Histogram Horse1');
subplot(1,4,4), plot(HistH2), title('Histogram Horse2');
disp('-------------Solved problem 2.1-----------------');


%%%%%%%%%%%%%%% ----- Problem 2.2 ------ %%%%%%%%%%%%%%%%

[E1Row,E1Col] = size(E1);
[E2Row,E2Col] = size(E2);
[H1Row,H1Col] = size(H1);
[H2Row,H2Col] = size(H2);
E1vsE1 = CalSimilarityScore(HistE1,HistE1,E1Row,E1Col,E1Row,E1Col);
E1vsE2 = CalSimilarityScore(HistE1,HistE2,E1Row,E1Col,E2Row,E2Col);
E1vsH1 = CalSimilarityScore(HistE1,HistH1,E1Row,E1Col,H1Row,H1Col);
E1vsH2 = CalSimilarityScore(HistE1,HistH2,E1Row,E1Col,H2Row,H2Col);
E1Scores = [E1vsE1 E1vsE2 E1vsH1 E1vsH2];
E2vsE2 = CalSimilarityScore(HistE2,HistE2,E2Row,E2Col,E2Row,E2Col);
E2vsE1 = CalSimilarityScore(HistE2,HistE1,E2Row,E2Col,E1Row,E1Col);
E2vsH1 = CalSimilarityScore(HistE2,HistH1,E2Row,E2Col,H1Row,H1Col);
E2vsH2 = CalSimilarityScore(HistE2,HistH2,E2Row,E2Col,H2Row,H2Col);
E2Scores = [E2vsE1 E2vsE2 E2vsH1 E2vsH2];
H1vsE1 = CalSimilarityScore(HistH1,HistE1,H1Row,H1Col,E1Row,E1Col);
H1vsE2 = CalSimilarityScore(HistH1,HistE2,H1Row,H1Col,E2Row,E2Col);
H1vsH1 = CalSimilarityScore(HistH1,HistH1,H1Row,H1Col,H1Row,H1Col);
H1vsH2 = CalSimilarityScore(HistH1,HistH2,H1Row,H1Col,H2Row,H2Col);
H1Scores = [H1vsE1 H1vsE2 H1vsH1 H1vsH2];
H2vsE1 = CalSimilarityScore(HistH2,HistE1,H2Row,H2Col,E1Row,E1Col);
H2vsE2 = CalSimilarityScore(HistH2,HistE2,H2Row,H2Col,E2Row,E2Col);
H2vsH1 = CalSimilarityScore(HistH2,HistH1,H2Row,H2Col,H1Row,H1Col);
H2vsH2 = CalSimilarityScore(HistH2,HistH2,H2Row,H2Col,H2Row,H2Col);
H2Scores = [H2vsE1 H2vsE2 H2vsH1 H2vsH2];

E1SortedScore = sort(E1Scores, 'descend');
E2SortedScore = sort(E2Scores, 'descend');
H1SortedScore = sort(H1Scores, 'descend');
H2SortedScore = sort(H2Scores, 'descend');
Animals = {E1, E2, H1, H2};

for i = 1:4
    imagesE1{find(E1SortedScore == E1Scores(i))} = Animals{i};
    imagesE2{find(E2SortedScore == E2Scores(i))} = Animals{i};
    imagesH1{find(H1SortedScore == H1Scores(i))} = Animals{i};
    imagesH2{find(H2SortedScore == H2Scores(i))} = Animals{i};
end

figure, subplot(3,2,1), imshow(E1), title('Original Elephant 1');
subplot(3,2,3), imshow(imagesE1{1}), title(['Rank 1, Similarity Score: ', num2str(E1SortedScore(1))]);
subplot(3,2,4), imshow(imagesE1{2}), title(['Rank 2, Similarity Score: ', num2str(E1SortedScore(2))]);
subplot(3,2,5), imshow(imagesE1{3}), title(['Rank 3, Similarity Score: ', num2str(E1SortedScore(3))]);
subplot(3,2,6), imshow(imagesE1{4}), title(['Rank 4, Similarity Score: ', num2str(E1SortedScore(4))]);

figure, subplot(3,2,1), imshow(E2), title('Original Elephant 2');
subplot(3,2,3), imshow(imagesE2{1}), title(['Rank 1, Similarity Score: ', num2str(E2SortedScore(1))]);
subplot(3,2,4), imshow(imagesE2{2}), title(['Rank 2, Similarity Score: ', num2str(E2SortedScore(2))]);
subplot(3,2,5), imshow(imagesE2{3}), title(['Rank 3, Similarity Score: ', num2str(E2SortedScore(3))]);
subplot(3,2,6), imshow(imagesE2{4}), title(['Rank 4, Similarity Score: ', num2str(E2SortedScore(4))]);

figure, subplot(3,2,1), imshow(H1), title('Original Horse 1');
subplot(3,2,3), imshow(imagesH1{1}), title(['Rank 1, Similarity Score: ', num2str(H1SortedScore(1))]);
subplot(3,2,4), imshow(imagesH1{2}), title(['Rank 2, Similarity Score: ', num2str(H1SortedScore(2))]);
subplot(3,2,5), imshow(imagesH1{3}), title(['Rank 3, Similarity Score: ', num2str(H1SortedScore(3))]);
subplot(3,2,6), imshow(imagesH1{4}), title(['Rank 4, Similarity Score: ', num2str(H1SortedScore(4))]);

figure, subplot(3,2,1), imshow(H2), title('Original Horse 1');
subplot(3,2,3), imshow(imagesH2{1}), title(['Rank 1, similarity score: ', num2str(H2SortedScore(1))]);
subplot(3,2,4), imshow(imagesH2{2}), title(['Rank 2, similarity score: ', num2str(H2SortedScore(2))]);
subplot(3,2,5), imshow(imagesH2{3}), title(['Rank 3, similarity score: ', num2str(H2SortedScore(3))]);
subplot(3,2,6), imshow(imagesH2{4}), title(['Rank 4, similarity score: ', num2str(H2SortedScore(4))]);
disp('-------------Solved problem 2.2-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem 3.1 ------ %%%%%%%%%%%%%%%%

Lena = imread('Lena.jpg');
[Lena24, beta1] = Embedding(Lena, 24);
[Lena87, beta2] = Embedding(Lena, 87);
if(beta1 == beta2)
    disp('Matched Beta Values');
else
    disp('Beta Values do not Match');
end
figure,
subplot(2,3,1), imshow(Lena), title('Original Lena');
subplot(2,3,2), imshow(Lena24), title('Reconstructed Lena B=24');
subplot(2,3,3), imshow(Lena - Lena24,[]), title('Difference B=24');
subplot(2,3,4), imshow(Lena), title('Original Lena');
subplot(2,3,5), imshow(Lena87), title('Reconstructed Lena B=87');
subplot(2,3,6), imshow(Lena - Lena87,[]), title('Difference B=87');
disp('-------------Solved problem 3.1-----------------');
pause;


%%%%%%%%%%%%%%% ----- Problem 3.2 ------ %%%%%%%%%%%%%%%%

[row,col]=size(beta1);
beta1 = reshape(beta1,[1 row*col]);
E = (Extraction(Lena24,24) == beta1);
disp('Percentage Similarity for Beta=24: ');
disp(sum(E(:)) / (row*col) * 100);

E = (Extraction(Lena87, 87) == beta1);
disp('Percentage Similarity for Beta=87: ');
disp(sum(E(:)) / (row*col) * 100);
disp('-------------Solved problem 3.2-----------------');