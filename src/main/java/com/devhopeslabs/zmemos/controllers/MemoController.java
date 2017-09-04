package com.devhopeslabs.zmemos.controllers;

import com.devhopeslabs.zmemos.models.Memo;
import com.devhopeslabs.zmemos.services.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemoController {

    @Autowired
    private MemoService memoService;

    @RequestMapping(method = RequestMethod.GET, value="/getMemos")//checked
    public List<Memo> getListOfMemosWithUserId(@RequestParam("user_id") String user_id){//takes the user id as param and gets the list of memos
        return memoService.getListOfMemosOfUserWithId(user_id);
    }
    @RequestMapping(method = RequestMethod.GET, value="/getAllMemos")//checked
    public List<Memo> getListOfMemos(){//gets all the memos without checking the status
        return memoService.getListOfMemos();
    }

    @RequestMapping(method = RequestMethod.PUT, value="/addMemo")//checked
    public List<Memo>  addMemoToUser(@RequestParam("user_id") String user_id,@RequestBody Memo memoToAdd){
        //takes the user id and memo to add as the params and adds the memo to the user
        return memoService.addMemoToUser(user_id,memoToAdd);
    }

    @RequestMapping(method = RequestMethod.POST, value="/updateMemo")//checked
    public List<Memo>  updateMemoOfUser(@RequestParam("user_id") String user_id,@RequestBody Memo memoToUpdate){
        //updates the passed memo of the user
        return memoService.updateMemoOfUser(user_id,memoToUpdate);
    }

    @RequestMapping(method = RequestMethod.GET, value="/getRecoverableMemos")//checked
    public List<Memo>  getRecoverableMemos(@RequestParam("user_id") String user_id){
        return memoService.getRecoverableMemos(user_id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/recoverMemo")//checked
    public List<Memo>  recoverMemo(@RequestParam("user_id") String user_id,@RequestBody Memo memoToRecover){
        return memoService.recoverMemo(user_id,memoToRecover);
    }

    @RequestMapping(method = RequestMethod.POST, value="/recoverListOfMemo")//checked
    public List<Memo>  recoverListOfMemos(@RequestParam("user_id") String user_id,@RequestBody List<Memo> memosToRecover){
        return memoService.recoverListOfMemos(user_id,memosToRecover);
    }

    @RequestMapping(method = RequestMethod.POST, value="/deleteMemo")//checked
    public List<Memo>  deleteMemoOfUser(@RequestParam("user_id") String user_id,@RequestBody Memo memoToDelete){
        return memoService.deleteMemoOfUser(user_id,memoToDelete);
    }

    @RequestMapping(method = RequestMethod.POST, value="/deleteListOfMemos")//checked
    public List<Memo>  deleteListOfMemosOfUser(@RequestParam("user_id") String user_id,@RequestBody List<Memo> memosToDelete){
        return memoService.deleteListOfMemosOfUser(user_id,memosToDelete);
    }
}
