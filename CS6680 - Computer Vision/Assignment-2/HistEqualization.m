function [enhancedIm, transFunc] = HistEqualization(inputIm)

[row, col] = size(inputIm);
pixelCount = row * col;

frequency = zeros(256,1);
probability = zeros(256,1);

for i = 1:row
    for j = 1:col
        pixelValue = inputIm(i,j);
        frequency(pixelValue) = frequency(pixelValue) + 1;
        probability(pixelValue) = frequency(pixelValue) / pixelCount;
    end
end


intermediateSum = 0;
cumulativeProbability = zeros(256,1);
outputPixelValues = zeros(256,1);

for i = 1:size(probability)
   intermediateSum = intermediateSum + probability(i);
   cumulativeProbability(i) = intermediateSum;
   outputPixelValues(i) = round(cumulativeProbability(i) * 255);
end

enhancedIm = uint8(zeros(size(inputIm,1), size(inputIm,2)));
for i = 1:row
    for j = 1:col
        enhancedIm(i,j) = outputPixelValues(inputIm(i,j));
    end
end

transFunc = outputPixelValues;

end

