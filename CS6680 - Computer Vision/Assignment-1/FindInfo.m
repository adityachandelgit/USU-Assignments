function [maxValue, minValue, meanValue, medianValue] = FindInfo(oriIm)
maxValue=-Inf;
minValue=Inf;
for p=1:numel(oriIm)
  if oriIm(p)>maxValue
   maxValue=oriIm(p);
  end
  if oriIm(p)<minValue
   minValue=oriIm(p);
  end
end

meanValue = sum(oriIm(:)) / numel(oriIm);

sortedOriIm = sort(oriIm, 2);
[row, col] = size(oriIm);
medianValue = uint8(zeros(row, 1));
for i = 1:row
    medianValue(i) = double(sortedOriIm(i, col/2)) / double(2) + double(sortedOriIm(i, (col/2) + 1)) / double(2);
end    
    