function [scaledIm, transFunc] = Scaling(inputIm, range)  

    oldMax = double(max(inputIm(:)));
    oldMin = double(min(inputIm(:)));
    deltaOld = oldMax - oldMin;
    
    if(range(1) < 0 || range(1) > 1 || range(2) < 0 || range(2) > 1 || range(1) >= range(2))
        error('Invalid Range')
    end
    
    newMin = double(range(1) * 255);
    newMax = double(range(2) * 255);
    deltaNew = newMax - newMin;
    
    [row, col] = size(inputIm);
    
    scaledIm = uint8(zeros(row, col));
    
    pixelMap = containers.Map('KeyType','int32','ValueType','any');    
    
    for i = 1:row
        for j = 1:col
            newPixelValue = ((double((inputIm(i, j)) - oldMin) * deltaNew) / deltaOld) + newMin;
            scaledIm(i, j) = newPixelValue;
            if(isKey(pixelMap, inputIm(i, j)) == 0)
                pixelMap(int32(inputIm(i, j))) = round(newPixelValue);
            end                
        end
    end
    
    transFunc = cell2mat(pixelMap.values);
    
end

