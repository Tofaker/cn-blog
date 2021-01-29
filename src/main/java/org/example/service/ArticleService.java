package org.example.service;

import org.example.mapper.ArticleMapper;
import org.example.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2021-01-29;   Time: 17:11
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public List<Article> queryAll() {
        return articleMapper.selectAll();
    }

    public List<Article> queryByUser(Integer userId) {
        return articleMapper.queryByUser(userId);
    }

    public Article queryById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public int aritcleModify(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return articleMapper.updateByPrimaryKey(article);
    }
}
