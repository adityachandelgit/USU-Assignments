function edgeHist = CalEdgeHist(im, bin)
    im = double(im);
    Gx=[-1 -2 -1; 0 0 0; 1 2 1];
    GxF=imfilter(im, Gx);
    Gy=[-1 0 1; -2 0 2; -1 0 1]; 
    GyF=imfilter(im, Gy);
    angles = atan2d(GxF(:),GyF(:)) + 360*(GxF(:)<0);
    
    edgeHist=zeros(1,bin);
    for n=1:length(angles)
        edgeHist(1,floor(angles(n)/(bin-2))+1)=edgeHist(1,floor(angles(n)/(bin-2))+1)+1;
    end
end

