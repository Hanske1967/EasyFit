update product set creationuser = 'INGE' where creationuser is null ;
update product set creationdate = updatedate where creationdate is null ;

update productcategory set creationuser = 'INGE' where creationuser is null ;
update productcategory set creationdate = updatedate where creationdate is null ;

update consumption set creationuser = updateuser where creationuser is null ;
update consumption set creationdate = updatedate where creationdate is null ;

update consumptiondetail set creationuser = updateuser where creationuser is null ;
update consumptiondetail set creationdate = updatedate where creationdate is null ;

update user set creationuser = 'HANS';

update unit set creationuser = 'INGE';

update unit set creationuser = 'INGE';