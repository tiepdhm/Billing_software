package in.tiepdhm.billingsoftware.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.tiepdhm.billingsoftware.io.ItemRequest;
import in.tiepdhm.billingsoftware.io.ItemResponse;
import in.tiepdhm.billingsoftware.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/admin/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse addItem(@RequestPart("item")String itemString, @RequestPart("file") MultipartFile file)
    {
        ObjectMapper objectMapper=new ObjectMapper();
        ItemRequest itemRequest = null;
        try{
            itemRequest= objectMapper.readValue(itemString,ItemRequest.class);
            return itemService.add(itemRequest,file);
        }
        catch (IOException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"An error occured while processing the JSON");
        }

    }

    @GetMapping("/items")
    public List<ItemResponse> readItems()
    {
        return itemService.fetchItems();

    }

    @DeleteMapping("/admin/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable String itemId)
    {
        try{
            itemService.deleteItem(itemId);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Not found");
        }


    }
}
