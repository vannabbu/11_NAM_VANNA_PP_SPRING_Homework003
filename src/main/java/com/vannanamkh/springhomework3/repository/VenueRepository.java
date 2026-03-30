package com.vannanamkh.springhomework3.repository;

import com.vannanamkh.springhomework3.model.Venues;
import com.vannanamkh.springhomework3.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface VenueRepository {
        @Results(id = "mapper" , value = {
                @Result(property = "venueId", column = "venue_id"),
                @Result(property = "venueName", column = "venue_name"),
        })
        @Select("SELECT * FROM venues LIMIT #{size} OFFSET ${(page - 1) * size}")
        List<Venues> getAllVenues(int page , int size) ;

        @ResultMap("mapper")
        @Select("SELECT * FROM venues")
        List<Venues> getAllVenuesItem();

        @ResultMap("mapper")
        @Select("SELECT * FROM venues where venue_id = #{id}")
        Venues getVenueByVenueId(Long id);

        @ResultMap("mapper")
        @Select("insert into venues (venue_name , location) values (#{venueName} , #{location})  RETURNING *")
        Venues createVenue(VenueRequest venueRequest);

        @ResultMap("mapper")
        @Select("update venues set venue_name = #{req.venueName} , location = #{req.location}  where venue_id = #{venueId} RETURNING *")
        Venues updateVenue(Long id , @Param("req") VenueRequest venueRequest);


        @ResultMap("mapper")
        @Select("delete from venues where venue_id = #{id}")
        Venues deleteVenue(Long id);
}
