package coolmol.ebookstore.dao;

import coolmol.ebookstore.entity.Icon;

import java.util.List;

public interface IconDao {
    Icon findOne(Integer id);

    List<Icon> getIcons();
}
