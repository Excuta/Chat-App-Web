package com.piedpiper.piperchat.data.repo;

import com.piedpiper.piperchat.data.model.message.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created By: Yahia
 */
@Repository
@SycnhronizedMessagesRepo
public class MessagesRepoWrapper implements MessagesRepo {
    private MessagesRepo messagesRepo;

    public MessagesRepoWrapper(MessagesRepo messagesRepo) {
        this.messagesRepo = messagesRepo;
    }

    @Override
    public List<Message> findAll() {
        return messagesRepo.findAll();
    }

    @Override
    public List<Message> findAll(Sort sort) {
        return messagesRepo.findAll(sort);
    }

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return messagesRepo.findAll(pageable);
    }

    @Override
    public List<Message> findAllById(Iterable<Long> longs) {
        return messagesRepo.findAllById(longs);
    }

    @Override
    public long count() {
        return messagesRepo.count();
    }

    @Override
    synchronized public void deleteById(Long aLong) {
        messagesRepo.deleteById(aLong);
    }

    @Override
    synchronized public void delete(Message entity) {
        messagesRepo.delete(entity);
    }

    @Override
    synchronized public void deleteAll(Iterable<? extends Message> entities) {
        messagesRepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        messagesRepo.deleteAll();
    }

    @Override
    synchronized public <S extends Message> S save(S entity) {
        return messagesRepo.save(entity);
    }

    @Override
    synchronized public <S extends Message> List<S> saveAll(Iterable<S> entities) {
        return messagesRepo.saveAll(entities);
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return messagesRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return messagesRepo.existsById(aLong);
    }

    @Override
    public void flush() {
        messagesRepo.flush();
    }

    @Override
    synchronized public <S extends Message> S saveAndFlush(S entity) {
        return messagesRepo.saveAndFlush(entity);
    }

    @Override
    synchronized public void deleteInBatch(Iterable<Message> entities) {
        messagesRepo.deleteInBatch(entities);
    }

    @Override
    synchronized public void deleteAllInBatch() {
        messagesRepo.deleteAllInBatch();
    }

    @Override
    public Message getOne(Long aLong) {
        return messagesRepo.getOne(aLong);
    }

    @Override
    public <S extends Message> Optional<S> findOne(Example<S> example) {
        return messagesRepo.findOne(example);
    }

    @Override
    public <S extends Message> List<S> findAll(Example<S> example) {
        return messagesRepo.findAll(example);
    }

    @Override
    public <S extends Message> List<S> findAll(Example<S> example, Sort sort) {
        return messagesRepo.findAll(example, sort);
    }

    @Override
    public <S extends Message> Page<S> findAll(Example<S> example, Pageable pageable) {
        return messagesRepo.findAll(example, pageable);
    }

    @Override
    public <S extends Message> long count(Example<S> example) {
        return messagesRepo.count(example);
    }

    @Override
    public <S extends Message> boolean exists(Example<S> example) {
        return messagesRepo.exists(example);
    }
}
