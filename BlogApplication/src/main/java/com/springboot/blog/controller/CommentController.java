package com.springboot.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(description = "Create Comment REST API")
    @ApiResponses(value= {
    		@ApiResponse(description="Comment successfully",responseCode="201"),
    		@ApiResponse(description="Comment Failed",responseCode="500")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(description = "Get All Comments By Post ID REST API")
    @ApiResponses(value= {
    		@ApiResponse(description="Comments Retrieve successfully",responseCode="200"),
    		@ApiResponse(description="Comment Not Found",responseCode="404")
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
    	List<CommentDto> list = commentService.getCommentsByPostId(postId);
    	if(list!= null && list.size()>0) {
    		
    		return new ResponseEntity<>(list,HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<>(list,HttpStatus.NOT_FOUND);
    	}
    }

    @Operation(description = "Get Single Comment By ID REST API")
    @ApiResponses(value= {
    		@ApiResponse(description="Comments Retrieve successfully",responseCode="200"),
    		@ApiResponse(description="Comment Not Found",responseCode="404")
    })
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "id") Long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        if(commentDto!= null ) {
    		
    		return new ResponseEntity<>(commentDto,HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<>(commentDto,HttpStatus.NOT_FOUND);
    	}
    }

    @Operation(description = "Update Comment By ID REST API")
    @ApiResponses(value= {
    		@ApiResponse(description="Comment Updated successfully",responseCode="202"),
    		@ApiResponse(description="Comment Update Failed",responseCode="500")
    })
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.ACCEPTED);
    }

    @Operation(description = "Delete Comment By ID REST API")
    @ApiResponses(value= {
    		@ApiResponse(description="Comment Delete successfully",responseCode="200"),
    		@ApiResponse(description="Comment Delete Failed",responseCode="500")
    })
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
