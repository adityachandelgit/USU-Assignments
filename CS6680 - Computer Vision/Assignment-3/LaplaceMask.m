function [ FilteredIm ] = LaplaceMask( im, mask ) 

    [row, col] = size(im);
    FilteredIm = uint8(zeros(row, col));
    im = padarray(im,[1 1]);
    
    for i = 2:row+1
        for j= 2:col+1
            s = 0;
            p = 1;
            for ii = i-1:i+1
                q = 1;
                for jj = j-1:j+1
                    s = s + (im(ii,jj) * mask(p, q));
                    q = q + 1;
                end
                p = p + 1;
            end
            FilteredIm(i-1, j-1) = s;
        end
    end
    
end

