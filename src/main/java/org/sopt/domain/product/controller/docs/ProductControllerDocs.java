package org.sopt.domain.product.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.domain.product.dto.ProductDetailResponse;
import org.sopt.domain.product.dto.ProductInfoResponse;
import org.sopt.domain.product.dto.ProductResponse;
import org.sopt.domain.product.dto.ReviewListResponse;
import org.sopt.domain.product.dto.StyleHintImageResponse;
import org.sopt.global.api.ApiResponse;

import java.util.List;

@Tag(name = "Product", description = "제품 관련 API")
public interface ProductControllerDocs {

    @Operation(
            summary = "전체 제품 목록 조회",
            description = "모든 제품의 목록을 최신순으로 조회합니다. 각 제품의 대표 이미지, 색상 코드, 가격 정보 등을 포함합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "제품 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            )
    })
    ApiResponse<List<ProductResponse>> getAllProducts();

    @Operation(
            summary = "제품 상세 정보 조회",
            description = "특정 제품의 상세 정보를 조회합니다. 제품 ID로 조회하며, 이미지, 색상, 가격 정보를 포함합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "제품 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = ProductInfoResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 제품",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<ProductInfoResponse> getProductById(
            @Parameter(description = "제품 ID", required = true, example = "1")
            Long productId
    );

    @Operation(
            summary = "제품 세부 정보 조회",
            description = "특정 제품의 세부 정보를 조회합니다. 상세 이미지, 상품 설명, 특징, 사이즈 안내 등의 상세 정보를 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "제품 세부 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = ProductDetailResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 제품",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<ProductDetailResponse> getProductDetail(
            @Parameter(description = "제품 ID", required = true, example = "1")
            Long productId
    );

    @Operation(
            summary = "제품 스타일 힌트 이미지 조회",
            description = "특정 제품에 대한 스타일 힌트 이미지 목록을 조회합니다. 제품의 다양한 코디 제안 및 스타일링 예시 이미지를 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "스타일 힌트 이미지 조회 성공",
                    content = @Content(schema = @Schema(implementation = StyleHintImageResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 제품",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<StyleHintImageResponse> getProductHintImage(
            @Parameter(description = "제품 ID", required = true, example = "1")
            Long productId
    );

    @Operation(
            summary = "제품 리뷰 목록 조회",
            description = "특정 제품에 대한 모든 리뷰를 조회합니다. 리뷰 제목, 내용, 별점, 작성일, 신체 정보(키, 성별), 추천 수, 구매 정보(사이즈, 색상, 핏) 등을 포함합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "리뷰 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = ReviewListResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 제품",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    ApiResponse<ReviewListResponse> getProductReviews(
            @Parameter(description = "제품 ID", required = true, example = "1")
            Long productId
    );
}