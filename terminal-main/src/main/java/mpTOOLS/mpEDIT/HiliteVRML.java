/*
 * Copyright (c) 1997, 1998 John Jensen. All rights reserved.
 *
 * This software is FREE FOR COMMERCIAL AND NON-COMMERCIAL USE,
 * provided the following condition is met.
 *
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for any purpose and without fee is hereby granted,
 * provided that any copy or derivative of this software or documentation
 * retaining the name "John Jensen" also retains this condition and the
 * following disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * CopyrightVersion 1.0
 */

package mpTOOLS.mpEDIT;

import java.awt.*;
import java.util.*;
import java.io.*;

public class HiliteVRML extends Hilite
{
	public HiliteVRML(LineMan l, int t, boolean a)
	{
		super(l,t,a);

		String temp[] = new String[] 
        {
			"MFColor",
			"MFFloat",
			"MFInt32",
			"MFLong",    // VRML 1.0
			"MFNode", 
			"MFRotation",
			"MFString",
			"MFVec2f",
			"MFVec3f",
			"SFBitMask",  // VRML 1.0
			"SFBool", 
			"SFColor",
			"SFEnum",     // VRML 1.0
			"SFFloat",
			"SFImage",
			"SFInt32",
			"SFLong",     // VRML 1.0
			"SFMatrix",   // VRML 1.0
			"SFNode", 
			"SFRotation",
			"SFString",
			"SFTime", 
			"SFVec2f",
			"SFVec3f",
			"ambientColor",     // VRML 1.0
			"ambientIntensity", 
			"appearance", 
			"attenuation", 
			"autoOffset", 
			"avatarSize", 
			"axisOfRotation", 
			"backUrl", 
			"bboxCenter",  // both VRML 1.0 and VRML 2.0
			"bboxSize",    // both VRML 1.0 and VRML 2.0
			"beamWidth", 
			"beginCap", 
			"bottomRadius", // both VRML 1.0 and VRML 2.0
			"bottomUrl", 
			"ccw", 
			"center",       // both VRML 1.0 and VRML 2.0
			"children", 
			"choice", 
			"collide", 
			"color",         // both VRML 1.0 and VRML 2.0
			"colorIndex", 
			"colorPerVertex", 
			"convex", 
			"coord", 
			"coordIndex",    // both VRML 1.0 and VRML 2.0
			"creaseAngle",   // both VRML 1.0 and VRML 2.0
			"crossSection", 
			"cutOffAngle",   // both VRML 1.0 and VRML 2.0
			"cycleInterval", 
			"cycleTime", 
			"depth",         // VRML 1.0
			"description",   // both VRML 1.0 and VRML 2.0
			"diffuseColor",  // both VRML 1.0 and VRML 2.0
			"directOutput", 
			"direction",     // both VRML 1.0 and VRML 2.0
			"diskAngle", 
			"dropOffRate",   // VRML 1.0
			"emissiveColor", // both VRML 1.0 and VRML 2.0
			"enabled", 
			"endCap", 
			"enterTime", 
			"eventIn",
			"eventOut",
			"exitTime", 
			"exposedField",
			"faceType",     // VRML 1.0
			"family",       // both VRML 1.0 and VRML 2.0
			"field",
			"fieldOfView", 
			"filename",     // VRML 1.0
			"focalDistance",
			"fogType", 
			"fontStyle", 
			"frontUrl", 
			"geometry", 
			"groundAngle",
			"groundColor", 
			"headlight",
			"height",        // both VRML 1.0 and VRML 2.0
			"heightAngle",   // VRML 1.0
			"horizontal", 
			"image",         // both VRML 1.0 and VRML 2.0
			"info", 
			"intensity",     // both VRML 1.0 and VRML 2.0
			"jump", 
			"justify", 
			"justification", // VRML 1.0
			"key", 
			"keyValue", 
			"language", 
			"leftToRight", 
			"leftUrl", 
			"length", 
			"level", 
			"location",     // both VRML 1.0 and VRML 2.0
			"loop",
			"map",          // VRML 1.0
			"material",
			"materialIndex", // VRML 1.0
			"matrix",       // VRML 1.0
			"maxAngle", 
			"maxBack", 
			"maxExtent", 
			"maxFront", 
			"maxPosition", 
			"minAngle", 
			"minBack", 
			"minFront", 
			"minPosition", 
			"mustEvaluate", 
			"name",        // VRML 1.0
			"normal", 
			"normalIndex", // both VRML 1.0 and VRML 2.0
			"normalPerVertex", 
			"numPoints",   // VRML 1.0
			"offset", 
			"on",          // both VRML 1.0 and VRML 2.0
			"orientation", // both VRML 1.0 and VRML 2.0
			"parameter",
			"parts",       // VRML 1.0
			"pitch",
			"point",       // both VRML 1.0 and VRML 2.0
			"position",    // both VRML 1.0 and VRML 2.0
			"priority", 
			"proxy", 
			"radius",      // both VRML 1.0 and VRML 2.0
			"range",       // both VRML 1.0 and VRML 2.0
			"renderCulling", // VRML 1.0
			"repeatS", 
			"repeatT", 
			"rightUrl", 
			"rotation",    // both VRML 1.0 and VRML 2.0
			"scale", 
			"scaleFactor", // VRML 1.0
			"scaleOrientation",  // both VRML 1.0 and VRML 2.0
			"shapeType",   // VRML 1.0
			"shininess",   // both VRML 1.0 and VRML 2.0
			"side", 
			"size",        // both VRML 1.0 and VRML 2.0
			"skyAngle", 
			"skyColor", 
			"solid",
			"source", 
			"spacing",     // both VRML 1.0 and VRML 2.0
			"spatialize", 
			"specularColor",  // both VRML 2.0 and VRML 2.0
			"speed", 
			"spine", 
			"startIndex",  // VRML 1.0
			"startTime",
			"stopTime",
			"string",    // both VRML 1.0 and VRML 2.0
			"style",     // both VRML 1.0 and VRML 2.0
			"texCoord", 
			"texCoordIndex",
			"texture",
			"textureCoordIndex",  // VRML 1.0
			"textureTransform",
			"title",
			"top", 
			"topToBottom", 
			"topUrl", 
			"translation",     // both VRML 1.0 and VRML 2.0
			"transparency",    // both VRML 1.0 and VRML 2.0
			"type", 
			"url",        
			"value",       // VRML 1.0
			"vector",      // both VRML 1.0 and VRML 2.0
			"vertexOrdering", // VRML 1.0
			"visibilityLimit", 
			"visibilityRange", 
			"whichChild",   // VRML 1.0
			"whichChoice",
			"width",        // VRML 1.0
			"wrapS",        // VRML 1.0
			"wrapT",        // VRML 1.0
			"xDimension", 
			"xSpacing", 
			"zDimension", 
			"zSpacing"	        
		};


		String temp2[] = new String[] 
		{
			"Anchor",
			"Appearance",
			"AsciiText",   // VRML 1.0
			"AudioClip",
			"Background",
			"Billboard",
			"Box",
			"Collision",
			"Color",
			"ColorInterpolator",
			"Cone",       // both VRML 1.0 and VRML 2.0
			"Coordinate",
			"Coordinate3", // VRML 1.0
			"CoordinateInterpolator",
			"Cube",       // VRML 1.0
			"Cylinder",   // both VRML 1.0 and VRML 2.0
			"CylinderSensor",
			"DEF",
			"DirectionalLight",  // both VRML 1.0 and VRML 2.0
			"EXTERNPROTO",
			"ElevationGrid",
			"Extrusion",
			"Fog",
			"FontStyle",        // both VRML 1.0 and VRML 2.0
			"Group",
			"ImageTexture",
			"IndexedFaceSet",   // both VRML 1.0 and VRML 2.0
			"IndexedLineSet",   // both VRML 1.0 and VRML 2.0
			"Info",             // VRML 1.0
			"Inline",
			"LOD",              // both VRML 1.0 and VRML 2.0
			"Material",         // both VRML 1.0 and VRML 2.0
			"MaterialBinding",  // VRML 1.0
			"MatrixTransform",  // VRML 1.0
			"MovieTexture",
			"NavigationInfo",
			"Normal",           // both VRML 1.0 and VRML 2.0
			"NormalBinding",    // VRML 1.0
			"NormalInterpolator",
			"OrientationInterpolator",
			"OrthographicCamera", // VRML 1.0
			"PROTO",
			"PerspectiveCamera",  // VRML 1.0
			"PixelTexture",
			"PlaneSensor",
			"PointLight",         // both VRML 1.0 and VRML 2.0
			"PointSet",           // both VRML 1.0 and VRML 2.0
			"PositionInterpolator",
			"ProximitySensor",
			"Rotation",     // VRML 1.0
			"ScalarInterpolator",
			"Scale",        // VRML 1.0
			"Script",
			"Separator",    // VRML 1.0
			"Shape",
			"ShapeHints",   // VRML 1.0
			"Sound",
			"Sphere",       // both VRML 1.0 and VRML 2.0
			"SphereSensor",
			"SpotLight",    // both VRML 1.0 and VRML 2.0
			"Switch",       // both VRML 1.0 and VRML 2.0
			"Text",
			"Texture2",     // VRML 1.0
			"Texture2Transform", // VRML 1.0
			"TextureCoordinate",
			"TextureCoordinate2", // VRML 1.0
			"TextureTransform",
			"TimeSensor",
			"TouchSensor",
			"Transform",    // both VRML 1.0 and VRML 2.0
			"Translation",  // VRML 1.0
			"USE",
			"Viewpoint",
			"VisibilitySensor",
			"WWWAnchor",    // VRML 1.0
			"WWWInline",    // VRML 1.0
			"WorldInfo"
		};

		int i,max;

        max = temp.length;
		keys = new char[max][];

		for (i=0;i<max;i++)
			keys[i] = temp[i].toCharArray();
			
		max = temp2.length;

		keys2 = new char[max][];

		for (i=0;i<max;i++)
			keys2[i] = temp2[i].toCharArray();
	}

	protected void scanLine(int i)
	{
		char	c,d,e;
		String	w;
		boolean	isword,inword,incharliteral;
		int		pos,start,max;

		max = fillBuffer(i);

		pos = -1;
		inword = incharliteral =false;
		start = 0;
		c = d = 0;
		keyCt = 0;

		if (inLiteral)
		{
			keyStarts[keyCt] = 0;
		}

		while (++pos <= max)
		{
			e = d;
			d = c;

			if (pos < max)
				c = buffer[pos];
			else
				c = 0;

			if ((c == '"') && (d != '\\') && (d != '\''))
			{
				if (!inLiteral)
				{
					inLiteral = true;
					keyStarts[keyCt] = pos;
				}
				else
				{
					inLiteral = false;
					keyEnds[keyCt] = pos+1;
					keyTypes[keyCt] = QUOTE;
					keyCt++;
				}
				inword = false;
			}

			if (inLiteral)
			    continue;
			    
			if ((c =='\'') && ((d != '\\' ) || ((d == '\\' ) && (e == '\\' ))))
			{
				if(!incharliteral)
				{
					incharliteral = true;
					keyStarts[keyCt] = pos;
				}
				else
				{
					incharliteral = false;
					keyEnds[keyCt] = pos+1;
					keyTypes[keyCt] = QUOTE;
					keyCt++;
				}
				inword = false;
			}

			if (incharliteral)
				continue;	

            // If the current character is upper case or lower case, we might be in a word
			if (((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <='z')) || ((c >= '0') && (c<='9')) || ( c == '_' ))
			{
				if (!inword && ((d < 'A') || (d > 'Z')) && ((d < 'a') || (d > 'z')))
				{
					keyStarts[keyCt] = start = pos;
					inword = true;
				}

				continue;
			}
			else
			{
				if (inword)
				{
					if ((c < 'A') || (c > 'Z'))
					{
						if (matchKeys(start,pos))
						{
							keyEnds[keyCt] = pos;
							keyTypes[keyCt] = KEYWORD;
							keyCt++;
						}
						else if (matchKeys2(start,pos))
						{
						    keyEnds[keyCt] = pos;
						    keyTypes[keyCt] = KEYWORD2;
						    keyCt++;
						}
					}
					inword = false;
					continue;
				}
			}
			
			if ((c == '#') && !(inComment || inLiteral))
			{
				keyStarts[keyCt] = pos;
				keyEnds[keyCt] = max;
				keyTypes[keyCt] = COMMENT;
				keyCt++;
				pos = max;	// bail, we don't want any more parsing on this line
			}    
		}

		if (inComment)
		{
			keyEnds[keyCt] = max;
			keyTypes[keyCt] = COMMENT;
			keyCt++;
		}

		if (inLiteral)
		{
			keyEnds[keyCt] = max;
			keyTypes[keyCt] = QUOTE;
			keyCt++;
		}

		LineInfo hi = lines.getLineInfo(i);

		hi.inComment = inComment;
		hi.inLiteral = inLiteral;
		hi.keyCt = keyCt;
		hi.keyStarts = new int[keyCt];
		hi.keyEnds = new int[keyCt];
		hi.keyTypes = new byte[keyCt];

		for (int j=0; j<keyCt; j++)
		{
			hi.keyStarts[j] = keyStarts[j];
			hi.keyEnds[j] = keyEnds[j];
			hi.keyTypes[j] = keyTypes[j];
		}
	}
}
