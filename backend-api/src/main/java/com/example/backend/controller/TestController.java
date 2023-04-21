package com.example.backend.controller;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.domain.Testes;

import com.example.backend.service.TestService;
import com.example.backend.service.UserService;

@RestController
@CrossOrigin
class TestController{

    @Autowired
    TestService testService;
    UserService userService;

    // public TestController(UserService userService) {
    //     this.userService = userService;
    // }
 
    @PostMapping("/trim-image")
    public String trimImage(@RequestParam("image") File imageFile,
                            @RequestParam("x") int x,
                            @RequestParam("y") int y,
                            @RequestParam("width") int width,
                            @RequestParam("height") int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageFile);
        BufferedImage trimmedImage = originalImage.getSubimage(x, y, width, height);
        BufferedImage circleImage = createCircleImage(trimmedImage);
        String fileName = "trimmed-image.png";
        ImageIO.write(circleImage, "png", new File(fileName));
        return fileName;
    }
    
    private BufferedImage createCircleImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage circle = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = circle.createGraphics();
        g.setClip(getCircle(width, height));
        g.drawImage(image, 0, 0, null);
        return circle;
    }
    
    private Shape getCircle(int width, int height) {
        int diameter = Math.min(width, height);
        int x = (width - diameter) / 2;
        int y = (height - diameter) / 2;
        return new Ellipse2D.Double(x, y, diameter, diameter);
    }
    
    @GetMapping(path="/testes/{id}")
    public Testes getTestes(@PathVariable("id") Integer id){
        Testes a = testService.getTestes(id);
        System .out.println(a);
        return a;
    }

    @GetMapping(path="/itigo/{num}")
    public Testes getItigo(@PathVariable("num") Integer num){
        Testes a = testService.getTestes(num);
        System.out.println(a);
        return a;
    }

    @PostMapping(path="/api/flavors")
public ResponseEntity<String> receiveFlavors(@RequestBody Map<String, Object> requestBody) {
    List<Integer> selectedFlavors = (List<Integer>) requestBody.get("flavors");
    List<Integer> selectedPurposes = (List<Integer>) requestBody.get("purposes");
    List<Integer> selectedComponents = (List<Integer>) requestBody.get("components");
    List<Integer> selectedPrices = (List<Integer>) requestBody.get("prices");
    
    System.out.println("これは味コードだよ");
    selectedFlavors.forEach(System.out::println);
    
    System.out.println("これは目的コードだよ");
    selectedPurposes.forEach(System.out::println);

    System.out.println("これは成分コードだよ");
    selectedComponents.forEach(System.out::println);

    System.out.println("これは価格コードだよ");
    selectedPrices.forEach(System.out::println);

    List<Testes> maguro = testService.findTestes(selectedFlavors,selectedPurposes,selectedComponents,selectedPrices);
    
    maguro.forEach(System.out::println);
    
    
    // do something with selectedFlavors and selectedPurposes
    return ResponseEntity.ok("コントローラーに届いたよ");
}


    @GetMapping(path="/hello")
    public String hello(){
        return "hello world!!";
    }

    @GetMapping(path="/users")
    public List<Testes> users(){
        return testService.findAllEntities();
    }

}