/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import DomainModel.ChatLieu;
import ViewModel.ChatLieuViewModel;
import java.util.List;

/**
 *
 * @author Lvh9x
 */
public interface ChatLieuService {

    public String themChatLieu(ChatLieu c);

    public String suaChatLieu(ChatLieu c);

    public String xoaChatLieu(ChatLieu c);

    public List<ChatLieuViewModel> GetAll();
}
