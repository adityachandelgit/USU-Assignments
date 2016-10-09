function [filteredIm] = AverageFiltering (im, mask)

    [a,b] = size(mask);
    if(issymmetric(mask) == 0 || mod(a,2) == 0 || mod(b,2) == 0 || a ~= b)
        error('Incorrect mask');
    end
    
    sumMask = sum(mask(:));
    halfMask = floor(a/2);
    [row,col] = size(im);    
    filteredIm = uint8(zeros(row, col));    
    im = padarray(im,[halfMask halfMask]);
    
    x = 1;
    for i = (halfMask+1):(row+halfMask)
        y = 1;
        for j = (halfMask+1):(col+halfMask)
            total = 0;
            total = uint32(total);
            p = 1;
            for k = i-halfMask:halfMask+i
                q = 1;
                for l = j-halfMask:j+halfMask
                    total = total + uint32((im(k,l) * mask(p, q)));
                    q = q + 1;
                end
                p = p + 1;
            end           
            filteredIm(x,y) = total / sumMask;
            y = y + 1;
        end
        x = x + 1;
    end
end

