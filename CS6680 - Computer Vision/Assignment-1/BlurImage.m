function [blurredIm] = BlurImage(oriIm)
[row, col, dep] = size(oriIm);
blurredIm = uint8(zeros(row, col, dep));
for x = 1:4:row
    for y = 1:4:col
        for z = 1:dep
            blurredIm(x:x+3, y:y+3, z) = floor(mean2(oriIm(x:x+3, y:y+3, z)));
        end
    end
end
end

