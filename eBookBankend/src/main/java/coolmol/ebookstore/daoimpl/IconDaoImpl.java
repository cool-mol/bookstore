package coolmol.ebookstore.daoimpl;

import coolmol.ebookstore.dao.IconDao;
import coolmol.ebookstore.entity.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import coolmol.ebookstore.repository.IconRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class IconDaoImpl implements IconDao {
    @Autowired
    IconRepository iconRepository;

    @Override
    public Icon findOne(Integer id) {
        Optional<Icon> icon = iconRepository.findById(id);
        return icon.orElse(null);
    }

    @Override
    public List<Icon> getIcons(){
        return iconRepository.findAll();
    }
}
