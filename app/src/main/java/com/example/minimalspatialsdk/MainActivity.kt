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
        fillBoardWithTokens()
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
        val boardSize = 2.4f // Optimized for viewport
        val lineThickness = 0.1f // Thicker lines for better visibility
        val boardCenterY = 1.5f // Center of visible area
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
    
    private fun createXToken(position: Vector3) {
        val size = 0.12f
        val thickness = 0.04f
        
        // Create X with four small cubes positioned diagonally from center
        val offset = size / 2f
        
        // Top-left piece
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness, -thickness, -thickness/2),
                    Vector3(thickness, thickness, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x - offset, position.y + offset, position.z)))
            )
        )
        
        // Top-right piece
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness, -thickness, -thickness/2),
                    Vector3(thickness, thickness, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x + offset, position.y + offset, position.z)))
            )
        )
        
        // Center piece
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness, -thickness, -thickness/2),
                    Vector3(thickness, thickness, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(position))
            )
        )
        
        // Bottom-left piece
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness, -thickness, -thickness/2),
                    Vector3(thickness, thickness, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x - offset, position.y - offset, position.z)))
            )
        )
        
        // Bottom-right piece
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness, -thickness, -thickness/2),
                    Vector3(thickness, thickness, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x + offset, position.y - offset, position.z)))
            )
        )
        
        // Connect the dots with thin diagonal lines
        // Top-left to bottom-right connector
        val midOffset = offset / 2f
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness/2, -thickness/2, -thickness/2),
                    Vector3(thickness/2, thickness/2, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x - midOffset, position.y + midOffset, position.z)))
            )
        )
        
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness/2, -thickness/2, -thickness/2),
                    Vector3(thickness/2, thickness/2, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x + midOffset, position.y - midOffset, position.z)))
            )
        )
        
        // Top-right to bottom-left connector
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness/2, -thickness/2, -thickness/2),
                    Vector3(thickness/2, thickness/2, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x + midOffset, position.y + midOffset, position.z)))
            )
        )
        
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-thickness/2, -thickness/2, -thickness/2),
                    Vector3(thickness/2, thickness/2, thickness/2)
                ),
                Material().apply {
                    baseColor = Color4(0.0f, 0.0f, 1.0f, 1.0f) // Blue
                },
                Transform(Pose(Vector3(position.x - midOffset, position.y - midOffset, position.z)))
            )
        )
    }
    
    private fun createOToken(position: Vector3) {
        val radius = 0.15f  // Smaller radius
        val thickness = 0.04f
        val segments = 16
        
        // Create a ring by making small boxes arranged in a circle
        for (i in 0 until segments) {
            val angle = (i * 2 * Math.PI / segments).toFloat()
            val x = radius * kotlin.math.cos(angle)
            val y = radius * kotlin.math.sin(angle)
            
            Entity.create(
                listOf(
                    Mesh(Uri.parse("mesh://box")),
                    Box(
                        Vector3(-thickness, -thickness, -thickness/2),
                        Vector3(thickness, thickness, thickness/2)
                    ),
                    Material().apply {
                        baseColor = Color4(0.0f, 1.0f, 0.0f, 1.0f) // Green
                    },
                    Transform(
                        Pose(
                            Vector3(position.x + x, position.y + y, position.z)
                        )
                    )
                )
            )
        }
    }
    
    private fun fillBoardWithTokens() {
        val wallDistance = 5f
        val boardSize = 2.4f
        val boardCenterY = 1.5f
        val zOffset = -0.05f
        val cellSize = boardSize / 3f
        
        // The grid lines are at boardSize/6 from center
        // boardSize = 3, so grid lines are at +/- 0.5 from center
        val gridLineOffset = boardSize / 6f  // 0.5
        
        // Grid lines are at:
        // Top line: boardCenterY + 0.5 = 2.7
        // Bottom line: boardCenterY - 0.5 = 1.7
        
        // Position tokens in the center of each cell
        // Top row: above the top grid line
        val topRowY = boardCenterY + gridLineOffset + gridLineOffset/2f     // 2.2 + 0.5 + 0.25 = 2.95
        // Middle row: at board center
        val middleRowY = boardCenterY                                       // 2.2
        // Bottom row: below the bottom grid line  
        val bottomRowY = boardCenterY - gridLineOffset - gridLineOffset/2f  // 2.2 - 0.5 - 0.25 = 1.45
        
        // Move tokens closer to camera to avoid wall occlusion
        val tokenZ = wallDistance - 0.5f  // Bring tokens 0.5 units closer
        
        val positions = arrayOf(
            // Top row
            Vector3(-boardSize/3f, topRowY + 0.1f, tokenZ),  // Top row X's moved up
            Vector3(0f, topRowY + 0.1f, tokenZ),  // Top center O stays up
            Vector3(boardSize/3f, topRowY + 0.1f, tokenZ),  // Top row X's moved up
            // Middle row
            Vector3(-boardSize/3f, middleRowY, tokenZ),
            Vector3(0f, middleRowY, tokenZ),
            Vector3(boardSize/3f, middleRowY, tokenZ),
            // Bottom row
            Vector3(-boardSize/3f, bottomRowY - 0.1f, tokenZ),  // Bottom row X's moved down
            Vector3(0f, bottomRowY - 0.1f, tokenZ),  // Bottom center O stays down
            Vector3(boardSize/3f, bottomRowY - 0.1f, tokenZ)  // Bottom row X's moved down
        )
        
        // Create a pattern: X-O-X / O-X-O / X-O-X
        val pattern = arrayOf(true, false, true, false, true, false, true, false, true) // true = X, false = O
        
        for (i in positions.indices) {
            if (pattern[i]) {
                createXToken(positions[i])
            } else {
                createOToken(positions[i])
            }
        }
    }
    
    private fun testCoordinateSystem() {
        // Comprehensive viewport test
        experimentViewportBounds()
    }
    
    private var experimentIndex = 1
    
    private fun experimentViewportBounds() {
        when (experimentIndex) {
            0 -> testYBounds()
            1 -> testXBounds()
            2 -> testZBounds()
            3 -> testRotations()
            4 -> testOptimalBoardPosition()
            else -> {
                // Reset for final test
                experimentIndex = 0
                testYBounds()
            }
        }
    }
    
    private fun testYBounds() {
        // Test Y bounds - create markers at different Y positions
        val z = 5f
        for (y in -20..40) {
            val yPos = y * 0.1f
            val intensity = if (y % 10 == 0) 1.0f else 0.5f
            val color = Color4(intensity, yPos / 4f, 0f, 1f)
            createSmallTestCube(Vector3(0f, yPos, z), color)
            
            // Major markers
            if (y % 10 == 0) {
                createTestCube(Vector3(-0.5f, yPos, z), Color4(1f, 1f, 1f, 1f), "Y=$yPos")
            }
        }
        
        // Mark expected viewport center
        createTestCube(Vector3(0f, 1.0f, z), Color4(0f, 1f, 0f, 1f), "Expected Center")
    }
    
    private fun testXBounds() {
        // Test X bounds - create markers at different X positions
        val z = 5f
        val y = 1.0f // Use center Y
        
        for (x in -40..40) {
            val xPos = x * 0.1f
            val intensity = if (x % 10 == 0) 1.0f else 0.5f
            val color = Color4(0f, intensity, xPos / 4f + 0.5f, 1f)
            createSmallTestCube(Vector3(xPos, y, z), color)
            
            // Major markers
            if (x % 10 == 0) {
                createTestCube(Vector3(xPos, y + 0.3f, z), Color4(1f, 1f, 1f, 1f), "X=$xPos")
            }
        }
    }
    
    private fun testZBounds() {
        // Test Z bounds - create markers at different Z positions
        val y = 1.0f
        
        for (z in 20..80) {
            val zPos = z * 0.1f
            val size = 0.02f * (zPos / 5f) // Scale with distance
            val color = Color4(zPos / 8f, 0f, 1f - zPos / 8f, 1f)
            
            Entity.create(
                listOf(
                    Mesh(Uri.parse("mesh://box")),
                    Box(
                        Vector3(-size, -size, -size),
                        Vector3(size, size, size)
                    ),
                    Material().apply {
                        baseColor = color
                    },
                    Transform(Pose(Vector3(0f, y, zPos)))
                )
            )
            
            // Major markers
            if (z % 10 == 0) {
                createTestCube(Vector3(0.5f, y, zPos), Color4(1f, 1f, 0f, 1f), "Z=$zPos")
            }
        }
    }
    
    private fun testRotations() {
        // Test different rotations for X marks
        val z = 5f
        val y = 1.0f
        
        // Test different rotation angles
        for (i in 0..7) {
            val angle = i * 22.5f // 0, 22.5, 45, 67.5, 90, etc.
            val radians = Math.toRadians(angle.toDouble())
            val sin = Math.sin(radians / 2).toFloat()
            val cos = Math.cos(radians / 2).toFloat()
            
            // Create rotated line
            Entity.create(
                listOf(
                    Mesh(Uri.parse("mesh://box")),
                    Box(
                        Vector3(-0.3f, -0.05f, -0.05f),
                        Vector3(0.3f, 0.05f, 0.05f)
                    ),
                    Material().apply {
                        baseColor = Color4(0f, angle / 180f, 1f - angle / 180f, 1f)
                    },
                    Transform(
                        Pose(
                            Vector3(-2f + i * 0.5f, y, z),
                            Quaternion(0f, 0f, sin, cos)
                        )
                    )
                )
            )
        }
    }
    
    private fun testOptimalBoardPosition() {
        // Find the best position for the tic-tac-toe board
        val z = 5f
        
        // Test different Y positions for board center
        for (i in 0..4) {
            val boardY = 0.5f + i * 0.5f
            
            // Draw mini grid
            val size = 0.3f
            // Horizontal lines
            createSmallTestCube(Vector3(-1.5f + i * 0.7f, boardY - size/3, z), Color4(1f, 0f, 0f, 1f))
            createSmallTestCube(Vector3(-1.5f + i * 0.7f, boardY + size/3, z), Color4(1f, 0f, 0f, 1f))
            
            // Vertical lines
            createSmallTestCube(Vector3(-1.5f + i * 0.7f - size/3, boardY, z), Color4(0f, 1f, 0f, 1f))
            createSmallTestCube(Vector3(-1.5f + i * 0.7f + size/3, boardY, z), Color4(0f, 1f, 0f, 1f))
            
            // Center marker
            createSmallTestCube(Vector3(-1.5f + i * 0.7f, boardY, z), Color4(1f, 1f, 0f, 1f))
        }
    }
    
    private fun createTestCube(position: Vector3, color: Color4, label: String) {
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-0.1f, -0.1f, -0.1f),
                    Vector3(0.1f, 0.1f, 0.1f)
                ),
                Material().apply {
                    baseColor = color
                },
                Transform(Pose(position))
            )
        )
    }
    
    private fun createSmallTestCube(position: Vector3, color: Color4) {
        Entity.create(
            listOf(
                Mesh(Uri.parse("mesh://box")),
                Box(
                    Vector3(-0.05f, -0.05f, -0.05f),
                    Vector3(0.05f, 0.05f, 0.05f)
                ),
                Material().apply {
                    baseColor = color
                },
                Transform(Pose(position))
            )
        )
    }
}