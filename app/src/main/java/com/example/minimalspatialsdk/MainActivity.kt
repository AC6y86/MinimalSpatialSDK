package com.example.minimalspatialsdk

import android.net.Uri
import com.meta.spatial.core.Entity
import com.meta.spatial.core.Pose
import com.meta.spatial.core.Quaternion
import com.meta.spatial.core.Vector3
import com.meta.spatial.toolkit.AppSystemActivity
import com.meta.spatial.toolkit.Box
import com.meta.spatial.toolkit.Material
import com.meta.spatial.toolkit.Mesh
import com.meta.spatial.toolkit.Transform
import com.meta.spatial.toolkit.Color4

class MainActivity : AppSystemActivity() {
    
    override fun onSceneReady() {
        super.onSceneReady()
        
        createWalls()
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
}