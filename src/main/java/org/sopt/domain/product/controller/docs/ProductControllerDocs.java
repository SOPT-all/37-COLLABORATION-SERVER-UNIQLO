package org.sopt.domain.product.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.domain.product.dto.ProductInfoResponse;
import org.sopt.domain.product.dto.ProductResponse;
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
}