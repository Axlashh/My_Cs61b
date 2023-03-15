import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */


    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        double ulrlon = params.get("lrlon");
        double uullon = params.get("ullon");
        double uullat = params.get("ullat");
        double ulrlat = params.get("lrlat");
        if (ulrlon < MapServer.ROOT_ULLON || uullon > MapServer.ROOT_LRLON
            || uullat < MapServer.ROOT_LRLAT || ulrlat > MapServer.ROOT_ULLAT) {
            results.put("query_success", false);
            return results;
        }
        double uLonDPP = (ulrlon - uullon) / params.get("w");
        double LonDPP = (MapServer.ROOT_LRLON - MapServer.ROOT_ULLON) / MapServer.TILE_SIZE;
        int layer = 0;
        for ( ; layer < 7; layer += 1) {
            if (LonDPP < uLonDPP) {
                break;
            }
            LonDPP /= 2;
        }
        double LonUNIT = LonDPP;
        double LatUNIT = (MapServer.ROOT_ULLAT - MapServer.ROOT_LRLAT) / (Math.pow(2, layer) * MapServer.TILE_SIZE);
        int raster_ulx = (int) ((uullon - MapServer.ROOT_ULLON) / (LonUNIT * MapServer.TILE_SIZE));
        int raster_lrx = (int) ((ulrlon - MapServer.ROOT_ULLON) / (LonUNIT * MapServer.TILE_SIZE));
        int raster_uly = (int) ((MapServer.ROOT_ULLAT - uullat) / (LatUNIT * MapServer.TILE_SIZE));
        int raster_lry = (int) ((MapServer.ROOT_ULLAT - ulrlat) / (LatUNIT * MapServer.TILE_SIZE));
        results.put("raster_ul_lon", raster_ulx * LonUNIT * MapServer.TILE_SIZE + MapServer.ROOT_ULLON);
        results.put("raster_lr_lon", (raster_lrx + 1) * LonUNIT * MapServer.TILE_SIZE + MapServer.ROOT_ULLON);
        results.put("raster_ul_lat", - raster_uly * LatUNIT * MapServer.TILE_SIZE + MapServer.ROOT_ULLAT);
        results.put("raster_lr_lat", - (raster_lry + 1) * LatUNIT * MapServer.TILE_SIZE + MapServer.ROOT_ULLAT);
        int nx = raster_lrx - raster_ulx + 1;
        int ny = raster_lry - raster_uly + 1;
        String[][] render_grid = new String[ny][nx];
        for (int i = raster_uly; i <= raster_lry; i += 1) {
            for (int j = raster_ulx; j <= raster_lrx; j += 1) {
                render_grid[i - raster_uly][j - raster_ulx] = "d" + layer + "_x" + j + "_y" + i + ".png";
            }
        }
        results.put("render_grid", render_grid);
        results.put("query_success", true);
        results.put("depth", layer);

        return results;
    }

}
