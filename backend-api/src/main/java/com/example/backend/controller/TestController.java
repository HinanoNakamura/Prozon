package com.example.backend.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.backend.domain.Product;
import com.example.backend.domain.Testes;

import com.example.backend.service.TestService;
import com.example.backend.service.UserService;

@RestController
@CrossOrigin
class TestController {

    @Autowired
    TestService testService;
    UserService userService;

    @PostMapping(path = "/api/flavors")
    public List<Testes> receiveFlavors(@RequestBody Map<String, Object> requestBody) {
        List<Integer> selectedFlavors = (List<Integer>) requestBody.get("flavors");
        List<Integer> selectedPurposes = (List<Integer>) requestBody.get("purposes");
        List<Integer> selectedComponents = (List<Integer>) requestBody.get("components");
        List<Integer> selectedPrices = (List<Integer>) requestBody.get("prices");

        // System.out.println("これは味コードだよ!");
        // selectedFlavors.forEach(System.out::println);

        List<Testes> maguro = testService.findTestes(selectedFlavors, selectedPurposes, selectedComponents,
                selectedPrices);

        // maguro.forEach(System.out::println);

        return maguro;
    }

    @GetMapping(path = "/users")
    public List<Testes> users() {
        System.out.println("成功");
        return testService.findAllEntities();
    }

    @PostMapping(path = "/api/addToFavorites")
    public ResponseEntity<Object> postMethodName(@RequestBody Product product) {
        System.out.println(product);
        if (testService.addToFavorites(product.getId())) {
            boolean result = testService.addToFavorites(product.getId());
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ログインに失敗しました。");
        }
    }

    @PostMapping(path = "/deleteFavorites")
    public boolean delete(@RequestBody Product product) {
        System.out.println("もんじゃ"+product.getId());
        boolean boo = testService.deleteFavorites(product.getId());
        System.out.println("もへじ"+boo);
        return boo;
    }

    @GetMapping(path = "/getFavorites/{userId}")
    public List<Testes> favorites(@PathVariable("userId") String userId){
        List<Integer> propro =testService.getFavoritesId(userId);
        List<Testes> testes = testService.getFavorites(propro);
        testes.forEach(System.out::println);
        return testes;
       
    } 

    @GetMapping(path = "/detail/{id}")
    public List<Testes> detail(@PathVariable("id") Integer id){
        System.out.print("もんじゃ" + id);
        return testService.getDetail(id);
    }


    //ランキング
    @GetMapping(path="/ranking")
    public List<Testes> ranking(){
        return testService.findRanking();
    }

    @GetMapping(path="/woman")
    public List<Testes> womanList(){
        return testService.womans();
    }

    @GetMapping(path="/man")
    public List<Testes> manlist(){
        return testService.mans();
    }

    @GetMapping(path="/dogs")
    public List<Testes> dogslist(){
        return testService.dogswan();
    }
    @GetMapping(path = "/fav/{flavorCode}/{id}")
    public List<Testes> fav(@PathVariable("flavorCode") Integer flavorCode,@PathVariable("id") Integer id  ){
        return testService.getfav(flavorCode,id);
    }
    
}