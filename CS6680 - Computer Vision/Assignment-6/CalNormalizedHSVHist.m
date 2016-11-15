function [Hist]=CalNormalizedHSVHist(Im, HbinNum, SbinNum, VbinNum)
ImHsv = rgb2hsv(Im);
H = ImHsv(:,:,1);
S = ImHsv(:,:,2);
V = ImHsv(:,:,3);
Hist = zeros(1, HbinNum * SbinNum * VbinNum);
[r,c,~]=size(ImHsv);
for i=1:r
    for j=1:c
        hBinVal = floor(H(i,j)*HbinNum) + 1;
        if(hBinVal > HbinNum)
            hBinVal = HbinNum;
        end
        sBinVal = floor(S(i,j)*SbinNum) + 1;
        if(sBinVal > SbinNum)
            sBinVal = SbinNum;
        end
        vBinVal = floor(V(i,j)*VbinNum) + 1;
        if(vBinVal > VbinNum)
            vBinVal = VbinNum;
        end
        idx = SbinNum * VbinNum * (hBinVal-1) + VbinNum * (sBinVal-1) + vBinVal;
        Hist(idx) = Hist(idx) + 1;
    end
end
Hist = Hist ./ (r*c);
end