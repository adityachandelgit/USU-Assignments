function [score] = CalSimilarityScore(histH, histG, rowH, colH, rowG, colG)
    H = rowH * colH;
    G = rowG * colG;
    total = 0;
    for i=1:length(histH)
        total = total + min(histH(i) * H, histG(i) * G);
    end
    score = total/min(H,G);
end