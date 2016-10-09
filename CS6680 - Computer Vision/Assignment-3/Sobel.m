function [ op ] = Sobel(im, option)

if option == 1
    d=double(im);
    counter = 1;
    for i=1:size(d,1)-2
        for j=1:size(d,2)-2
            Gx=((2*d(i+2,j+1)+d(i+2,j)+d(i+2,j+2))-(2*d(i,j+1)+d(i,j)+d(i,j+2)));
            Gy=((2*d(i+1,j+2)+d(i,j+2)+d(i+2,j+2))-(2*d(i+1,j)+d(i,j)+d(i+2,j)));
            counter = counter + 1;
            im(i,j)=sqrt(Gx.^2+Gy.^2);
            op = im;
        end
    end    
    op=uint8(op);        
    Thresh=111;
    op=max(op,Thresh);
    op(op==round(Thresh))=0;
    op=im2bw(op); 
    
elseif option == 2
    op=zeros(size(im));
    horz=[-1 0 1;-2 0 2; -1 0 1];
    vert=[-1 -2 -1;0 0 0; 1 2 1];
    im=double(im);
    counter = 1;
    for i=1:size(im,1)-2
        for j=1:size(im,2)-2
            Gx=sum(sum(horz.*im(i:i+2,j:j+2)));
            Gy=sum(sum(vert.*im(i:i+2,j:j+2)));            
            counter = counter + 1;
            op(i+1,j+1)=sqrt(Gx.^2+Gy.^2);
        end
    end
    op=uint8(op);        
    Thresh=200;
    op=max(op,Thresh);
    op(op==round(Thresh))=0;
    op=im2bw(op);      
else
    error('Invalid Option');    
end

end

