function [labelIm, num] = FindComponentLabels(im, se) 
[row, col] = size(im);
labelIm = zeros(row, col);
n = 0;
for i = 1:row
    for j = 1:col
        if im(i, j) == 1
            n = n+1;
            X0 = zeros(row, col);
            X0(i, j) = 1;
            while 1==1
                X1 = imdilate(X0, se) & im;             
                if X1 == X0
                    pos = X1==1;
                    im(pos) = 0;
                    labelIm(pos) = n;
                    break
                else
                    X0 = X1;
                end
            end
        end
    end
end
num = max(labelIm(:));
end

