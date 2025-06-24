package com.example.minimalspatialsdk

import android.net.Uri
import com.meta.spatial.castinputforward.CastInputForwardFeature
import com.meta.spatial.core.Entity
import com.meta.spatial.core.Pose
import com.meta.spatial.core.Quaternion
import com.meta.spatial.core.SpatialFeature
import com.meta.spatial.core.Vector3
import com.meta.spatial.toolkit.AppSystemActivity
import com.meta.spatial.toolkit.Box
import com.meta.spatial.toolkit.Material
import com.meta.spatial.toolkit.Mesh
import com.meta.spatial.toolkit.Transform
import com.meta.spatial.toolkit.Color4
import com.meta.spatial.vr.VRFeature

class MainActivity : AppSystemActivity() {
    
    override fun registerFeatures(): List<SpatialFeature> {
        val features = mutableListOf<SpatialFeature>()
        
        // Add VRFeature as the primary feature
        // features.add(VRFeature(this))
        
        // Add CastInputForwardFeature for input forwarding
        // features.add(CastInputForwardFeature(this))
        
        return features
    }
    
    override fun onSceneReady() {
        super.onSceneReady()
        
        createWalls()
        createTicTacToeBoard()
    }
    
    private fun createWalls() {
        val wallDistance = 5f // Distance from center
        val wallHeight = 3f
        val wallThickness = 0.1f
        val wallLength = 10f
        
        // Front wall (facing user)
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(Vector3(-wallLength/2, 0f, -wallThickness/2), Vector3(wallLength/2, wallHeight, wallThickness/2)),
                Material().apply {
                    baseColor = Color4(0.7f, 0.7f, 0.7f, 1.0f) // Gray
                },
                Transform(Pose(Vector3(0f, wallHeight/2, wallDistance)))
            )
        )
        
        // Back wall
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(Vector3(-wallLength/2, 0f, -wallThickness/2), Vector3(wallLength/2, wallHeight, wallThickness/2)),
                Material().apply {
                    baseColor = Color4(0.7f, 0.7f, 0.7f, 1.0f)
                },
                Transform(Pose(Vector3(0f, wallHeight/2, -wallDistance)))
            )
        )
        
        // Left wall
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(Vector3(-wallThickness/2, 0f, -wallLength/2), Vector3(wallThickness/2, wallHeight, wallLength/2)),
                Material().apply {
                    baseColor = Color4(0.7f, 0.7f, 0.7f, 1.0f)
                },
                Transform(Pose(Vector3(-wallDistance, wallHeight/2, 0f)))
            )
        )
        
        // Right wall
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(Vector3(-wallThickness/2, 0f, -wallLength/2), Vector3(wallThickness/2, wallHeight, wallLength/2)),
                Material().apply {
                    baseColor = Color4(0.7f, 0.7f, 0.7f, 1.0f)
                },
                Transform(Pose(Vector3(wallDistance, wallHeight/2, 0f)))
            )
        )
        
        // Floor (optional)
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(Vector3(-wallLength/2, -0.1f, -wallLength/2), Vector3(wallLength/2, 0f, wallLength/2)),
                Material().apply {
                    baseColor = Color4(0.5f, 0.5f, 0.5f, 1.0f) // Darker gray
                },
                Transform(Pose(Vector3(0f, 0f, 0f)))
            )
        )
    }
    
    private fun createTicTacToeBoard() {
        val wallDistance = 5f // Same as front wall distance
        val boardSize = 3f // Overall board size
        val lineThickness = 0.1f // Thicker lines for better visibility
        val boardCenterY = 2.2f // Higher position
        val zOffset = -0.05f // More offset from wall
        
        // Define positions for uniform grid
        // For equal squares in a 3x3 grid
        val cellSize = boardSize / 3f
        val leftLineX = -boardSize / 6f    // 1/3 from left edge
        val rightLineX = boardSize / 6f    // 1/3 from right edge  
        val topLineY = boardCenterY + boardSize / 6f
        val bottomLineY = boardCenterY - boardSize / 6f
        
        // Vertical lines
        // Left vertical line
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-lineThickness/2, -boardSize/2, -lineThickness/2),
                    Vector3(lineThickness/2, boardSize/2, lineThickness/2)
                ),
                Material().apply {
                    baseColor = Color4(1.0f, 0.0f, 0.0f, 1.0f) // Red
                },
                Transform(Pose(Vector3(leftLineX, boardCenterY, wallDistance + zOffset)))
            )
        )
        
        // Right vertical line
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-lineThickness/2, -boardSize/2, -lineThickness/2),
                    Vector3(lineThickness/2, boardSize/2, lineThickness/2)
                ),
                Material().apply {
                    baseColor = Color4(1.0f, 0.0f, 0.0f, 1.0f) // Red
                },
                Transform(Pose(Vector3(rightLineX, boardCenterY, wallDistance + zOffset)))
            )
        )
        
        // Horizontal lines
        // Top horizontal line
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-boardSize/2, -lineThickness/2, -lineThickness/2),
                    Vector3(boardSize/2, lineThickness/2, lineThickness/2)
                ),
                Material().apply {
                    baseColor = Color4(1.0f, 0.0f, 0.0f, 1.0f) // Red
                },
                Transform(Pose(Vector3(0f, topLineY, wallDistance + zOffset)))
            )
        )
        
        // Bottom horizontal line
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-boardSize/2, -lineThickness/2, -lineThickness/2),
                    Vector3(boardSize/2, lineThickness/2, lineThickness/2)
                ),
                Material().apply {
                    baseColor = Color4(1.0f, 0.0f, 0.0f, 1.0f) // Red
                },
                Transform(Pose(Vector3(0f, bottomLineY, wallDistance + zOffset)))
            )
        )
    }
}