package com.devhopeslabs.zmemos.services;

import com.devhopeslabs.zmemos.models.Memo;
import com.devhopeslabs.zmemos.models.User;
import com.devhopeslabs.zmemos.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoService {

    @Autowired
    private UserService userService;

    @Autowired
    private MemoRepository memoRepository;

    public List<Memo> getListOfMemos() {//gets all the memos without checking the status
        List<Memo>listOfMemos=new ArrayList<>();
        memoRepository.findAll().forEach(listOfMemos::add);//method reference stuff. this takes each of the memos and add it in a list
        return listOfMemos;
    }
    public List<Memo> getListOfMemosOfUserWithId(String id) {//takes only the memos of the user with this i and memos with status 'C' & 'R'
        List<Memo>listOfMemos=memoRepository.findByUserId(id);
        if (listOfMemos.stream().anyMatch(memo -> (memo.getStatus().equals("C") || memo.getStatus().equals("R")))) {
            return listOfMemos.stream().filter(memo -> (memo.getStatus().equals("C") || memo.getStatus().equals("R"))).collect(Collectors.toList());
        } else {
            return new ArrayList<>();//else return empty array
        }
    }
    public List<Memo> getListOfMemosOfUserWithIdIgnoreStatus(String id) {
        List<Memo>listOfMemos= memoRepository.findByUserId(id);//method reference stuff. this takes each of the memos and add it in a list
        return listOfMemos;
    }

    public String getNewMemoId(){
        return "Memo000"+ (memoRepository.count()+1);
    }

    public List<Memo>  addMemoToUser(String user_id, Memo memoToAdd) {
        //takes the user id and memo to add as the params and adds the memo to the user
        User userToAddMemo=userService.getUserWithId(user_id);//takes the user with given id
        if(userToAddMemo!=null){//if user exists then continue
            memoToAdd.setId(getNewMemoId());//find the memo's id
            memoToAdd.setUser(userToAddMemo);//if the memo is not having a user id then set it

            memoRepository.save(memoToAdd);//add the memo to db

            //take all the memos from db with current user id and status 'C' or 'R'
            return getListOfMemosOfUserWithId(user_id);
        }
        return new ArrayList<>();
    }

    public List<Memo> updateMemoOfUser(String user_id, Memo memoToUpdate) {//updates the passed memo of the user
        User userToUpdateMemo=userService.getUserWithId(user_id);//check if user exists or not
        if(userToUpdateMemo!=null){//if user exists
            memoRepository.save(memoToUpdate);//update the memo if not there then add it

            //take all the memos from db with current user id and status 'C' or 'R'
            return getListOfMemosOfUserWithId(user_id);

        }
        return new ArrayList<>();
    }


    public List<Memo> getRecoverableMemos(String user_id) {
        User userToUpdateMemo=userService.getUserWithId(user_id);
        if(userToUpdateMemo!=null){
            List<Memo>currentMemoList=memoRepository.findByUserId(user_id);//get current memos from db
            if(currentMemoList==null){//if empty return it
                return new ArrayList<>();
            }else if(currentMemoList.stream().anyMatch(m ->m.getStatus().equals("D"))){
                return currentMemoList.stream().filter(m->m.getStatus().equals("D")).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }
    public List<Memo> deleteMemoOfUser(String user_id, Memo memoToDelete) {
        User userToDeleteMemo=userService.getUserWithId(user_id);
        if(userToDeleteMemo!=null) {
            memoToDelete.setUser(userToDeleteMemo);
            memoToDelete.setStatus("D");
            memoRepository.save(memoToDelete);

            //take all the memos from db with current user id and status 'C' or 'R'
            return getListOfMemosOfUserWithId(user_id);
        }
        return new ArrayList<>();
    }
    public List<Memo> recoverMemo(String user_id, Memo memoToRecover) {
        User userToRecoverMemo=userService.getUserWithId(user_id);
        if(userToRecoverMemo!=null) {
            memoToRecover.setStatus("R");//set the status to 'R'
            memoRepository.save(memoToRecover);//save the updated one

            //take all the memos from db with current user id and status 'C' or 'R'
            return getListOfMemosOfUserWithId(user_id);
        }
        return new ArrayList<>();
    }

    public List<Memo> recoverListOfMemos(String user_id, List<Memo> memosToRecover) {
        User userToRecoverMemo=userService.getUserWithId(user_id);
        if(userToRecoverMemo!=null) {
            for (Memo memo:memosToRecover) {
                memo.setStatus("R");
                memo.setUser(userToRecoverMemo);
                memoRepository.save(memo);
            }
            //take all the memos from db with current user id and status 'C' or 'R'
            return getListOfMemosOfUserWithId(user_id);
        }
        return new ArrayList<>();
    }

    public List<Memo> deleteListOfMemosOfUser(String user_id, List<Memo> memosToDelete) {
        User userToDeleteMemo=userService.getUserWithId(user_id);
        if(userToDeleteMemo!=null) {
            for (Memo memo:memosToDelete) {
                memo.setStatus("D");//Set status to 'D'
                memo.setUser(userToDeleteMemo);//set user id for security
                memoRepository.save(memo);//add updated memo for future recovery
            }
            //take all the memos from db with current user id and status 'C' or 'R'
            return getListOfMemosOfUserWithId(user_id);
        }
        return new ArrayList<>();
    }
}
