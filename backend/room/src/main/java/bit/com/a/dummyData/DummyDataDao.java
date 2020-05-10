package bit.com.a.dummyData;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DummyDataDao {
    public int insertSi(SiDto siDto);
    
    public int insertGu(GuDto guDto);

    public int insertDong(DongDto dongDto);

    public int checkSi(SiDto siDto);

    public int checkGu(GuDto guDto);

    public int checkDong(DongDto dongDto);

    public int getSiId(SiDto siDto);

    public int getGuId(GuDto guDto);

    public int getDongId(DongDto dongDto);

    public SiDto getSi(int id);

    public GuDto getGu(int id);

    public DongDto getDong(int id);
}