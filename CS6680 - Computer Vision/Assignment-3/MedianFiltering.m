function [filteredIm] = MedianFiltering(im, mask)
    [row,col] = size(im);
    [a,b] = size(mask);
    if(issymmetric(mask) == 0 || mod(a,2) == 0 || mod(b,2) == 0 || a ~= b)
        error('Incorrect mask');
    end
    halfMask = floor(size(mask,2) / 2);   
    paddedIm = uint8(padarray(im, [halfMask halfMask]));
    filteredIm = zeros(size(im));
    for i=1:row
        for j=1:col
           p = i:i+halfMask+halfMask;
           q = j:j+halfMask+halfMask;
           temp = paddedIm(p,q);
           reshaped = reshape(temp,numel(temp),1);
           mask = reshape(mask, numel(mask),1);
           index = 0;
           op = zeros(1, sum(mask));
           for r=1:numel(reshaped)
                for l=1:mask(r)
                   index = index + 1;
                   op(1, index) = reshaped(r);
                end
           end
           filteredIm(i,j) = median(op);
        end
    end
    filteredIm = uint8(filteredIm);
end