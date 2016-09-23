function [varargout] = CalHist(inputIm, option)

    if(option > 3 || option < 0)
        error('Invalid Option')
    end
        
%     histArray = zeros(1,255);
    histMap = containers.Map('KeyType','uint32','ValueType','any');

    [row, col] = size(inputIm);    
    for i = 1:row
        for j = 1:col
%             histArray(1,inputIm(i,j)+1) = histArray(1,inputIm(i,j)+1)+1;
            if histMap.isKey(inputIm(i,j))
                histMap(inputIm(i,j)) = histMap(inputIm(i,j)) + 1;
            else 
                histMap(inputIm(i,j)) = 1;
            end
        end
    end
        
%     bar(cell2mat(histMap.keys), cell2mat(histMap.values));
    
    maxValue =  max(cell2mat(histMap.values));
    minValue =  min(cell2mat(histMap.values));
    
    normalizedValues = zeros(1, length(cell2mat(histMap.values)));
    
    i = 1;
    for v = cell2mat(histMap.values)
        normalizedValues(1, i) = (v - minValue) / (maxValue - minValue);
        i = i + 1;
    end
    
%     bar(cell2mat(histMap.keys), normalizedValues);
    
    histMapNorm = containers.Map(histMap.keys, normalizedValues);
    
    if(option == 1)
        varargout{1} = histMap;
    elseif(option == 2)
        varargout{1} = histMapNorm;
    elseif(option == 3)
        varargout{1} = histMap;
        varargout{2} = histMapNorm;
    end
        
    
end

