/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serviceImpl;

import DomainModel.ChatLieu;
import ViewModel.ChatLieuViewModel;
import java.util.List;
import repo.ChatLieuRepository;
import service.ChatLieuService;



/**
 *
 * @author Lvh9x
 */
public class ChatLieuServiceImpl implements ChatLieuService {

    private ChatLieuRepository chatLieuRepo = new ChatLieuRepository();

    @Override
    public String themChatLieu(ChatLieu c) {
        if (chatLieuRepo.Add(c)) {
            return "Thêm thành công";
        }
        return "Thêm không thành công";
    }

    @Override
    public String suaChatLieu(ChatLieu c) {
        if (chatLieuRepo.Update(c)) {
            return "Sửa thành công";
        }
        return "Sửa không thành công";
    }

    @Override
    public String xoaChatLieu(ChatLieu c) {
        if (chatLieuRepo.Delete(c)) {
            return "Xóa thành công";
        }
        return "Xóa không thành công";
    }

    @Override
    public List<ChatLieuViewModel> GetAll() {
        return chatLieuRepo.GetAll();
    }
    
    public ChatLieuViewModel GetCLByID(String id){
        for (ChatLieuViewModel x : chatLieuRepo.GetAll()) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        return null;
    }
}
